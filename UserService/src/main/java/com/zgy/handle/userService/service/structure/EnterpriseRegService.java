package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseRegDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.structure.StatusType;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.structure.EnterpriseRepository;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.service.email.IMailService;
import com.zgy.handle.userService.service.user.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class EnterpriseRegService extends SystemService<Enterprise, EnterpriseRegDTO> {
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private IndustryService industryService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private IMailService mailService;
    @Autowired
    private DepartmentAccountService departmentAccountService;
    public EnterpriseRegService(EnterpriseRepository enterpriseRepository) {
        super(enterpriseRepository);
        this.enterpriseRepository = enterpriseRepository;
    }

    /**
     * 获取当前用户的企业信息
     * @return
     */
    public Optional<Enterprise> getSelfEnterprise(){
        if(StringUtils.isNotBlank(getDepartId())){
            Optional<Enterprise> enterpriseOptional =  enterpriseRepository.findById(Long.valueOf(getDepartId()));
            return enterpriseOptional;
        }else {
            return Optional.empty();
        }

    }

    /**
     * 填充企业前缀
     * @param enterpriseId
     * @param prefix
     * @return
     */
    public Enterprise fillEnterprisePrefix(Long enterpriseId,String prefix){
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(enterpriseId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (enterpriseOptional.isPresent()){
            Enterprise enterprise = enterpriseOptional.get();
            enterprise.setPrefix(prefix);
            enterprise.setPrefixDate(sdf.format(new Date()));
            enterpriseRepository.save(enterprise);
            return enterprise;
        }else {
            throw new EntityNotFoundException("未发现id为:" + enterpriseId + "的企业");
        }
    }

    public Enterprise statusChange(Long enterpriseId, StatusType statusType, String statusValue){
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(enterpriseId);
        if (enterpriseOptional.isPresent()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Enterprise enterprise = enterpriseOptional.get();
            if (statusType.equals(StatusType.SH)){
                enterprise.setAuthorStatus(statusValue);
                Date now = new Date();
                enterprise.setAuthorDate(sdf.format(now));
            }else if (statusType.equals(StatusType.SQ)){
                enterprise.setCheckStatus(statusValue);
                Date now = new Date();
                enterprise.setCheckDate(sdf.format(now));
                enterprise.setCheckPerson(getPersonalName());

                if (statusValue.equals("授权失败")){
                    mailService.sendSimpleMail(enterprise.getEmail(),"handles审核","审核失败：原因");
                }else {
                    // 将企业同步到根节点

                }

            }else {
                throw new EntityNotFoundException("请传入正确的状态类型");
            }
            enterpriseRepository.save(enterprise);
            return enterprise;
        }else {
            throw new EntityNotFoundException("未发现id为:" + enterpriseId + "的企业");
        }
    }

    /**
     * 基于分页的动态查询
     * @param enterpriseDTO
     * @param pageable
     * @return
     */
    @Override
    public Page<Enterprise> findByDynamicQuery(Pageable pageable, EnterpriseRegDTO enterpriseDTO){
        Specification<Enterprise> specification = Specification
                .where(StringUtils.isBlank(enterpriseDTO.getName())? null : enterpriseRepository.blurStrQuery("name",enterpriseDTO.getName()))
                ;
        return enterpriseRepository.findAll(specification,pageable);
    }

    @Override
    public void beforeUpdate(EnterpriseRegDTO enterpriseDTO, Enterprise enterprise) {
        if (StringUtils.isNotBlank(enterpriseDTO.getParentId())){
            Optional<Enterprise> parentOptional = this.findById(Long.valueOf(enterpriseDTO.getParentId()));
            if (parentOptional.isPresent()){
                enterprise.setParent(parentOptional.get());
            }
        }
        if (StringUtils.isNotBlank(enterpriseDTO.getIndustryId())){
            Optional<Industry> industryOptional = industryService.findById(Long.valueOf(enterpriseDTO.getIndustryId()));
            if (industryOptional.isPresent()){
                enterprise.setBelongIndustry(industryOptional.get());
            }
        }
        enterprise.setNote(enterpriseDTO.getNote());
    }

    @Override
    public void postUpdate(Enterprise enterprise, EnterpriseRegDTO enterpriseRegDTO) {
        if (StringUtils.isNotBlank(getPersonalId())) {
            Optional<Account> accountOptional = accountService.findById(Long.valueOf(getPersonalId()));
            if (accountOptional.isPresent()) {
                departmentAccountService.deleteByAccountId(Long.valueOf(getPersonalId()));
                //log.info("获取到的部门信息为: " + department);
                departmentAccountService.setDepartmentAccount(accountOptional.get(), enterprise);
            }
        }
    }
}
