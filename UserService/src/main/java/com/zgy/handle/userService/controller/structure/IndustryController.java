package com.zgy.handle.userService.controller.structure;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.structure.convert.IndustryMapper;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.structure.IndustryDTO;
import com.zgy.handle.userService.service.structure.IndustryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "industry")
@Slf4j
@RequiredArgsConstructor
public class IndustryController {
    private final IndustryService industryService;
    private final IndustryMapper industryMapper;

    @GetMapping(value = "list")
    public ResponseCode<List<IndustryDTO>> list(IndustryDTO industryDTO){
        ResponseCode<List<IndustryDTO>> responseCode = ResponseCode.sucess();
        responseCode.setData(industryService.getIndustryDtoList());
        return responseCode;
    }

    @PostMapping(value = "update")
    public ResponseCode<Industry> update(@RequestBody IndustryDTO industryDTO){
        ResponseCode<Industry> responseCode = ResponseCode.sucess();
        Industry industry = industryMapper.toIndustry(industryDTO);
        if (industryDTO.getParentId() != null){
            Optional<Industry> parentOptional = industryService.findById(industryDTO.getParentId());
            if (parentOptional.isPresent()){
                industry.setParent(parentOptional.get());
            }
        }
        industry = industryService.update(StringUtils.isBlank(industryDTO.getId())?null:Long.valueOf(industryDTO.getId()),industry);
        responseCode.setData(industry);
        return responseCode;
    }
    @DeleteMapping(value = "delete/{id}")
    public ResponseCode<IndustryDTO> delete(@PathVariable("id") Long id){
        ResponseCode<IndustryDTO> responseCode = ResponseCode.sucess();
        Optional<Industry> optionalEnterprise = industryService.findById(id);
        if (optionalEnterprise.isPresent()){
            responseCode.setData(industryMapper.toIndustryDTO(optionalEnterprise.get()));
        }else {
            throw new EntityNotFoundException("找不到id为:" + id + "的数据");
        }
        industryService.delete(id);
        return responseCode;
    }
}
