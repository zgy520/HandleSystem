package com.zgy.handle.userService.controller.structure;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.structure.convert.IndustryMapper;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.structure.IndustryDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.structure.IndustryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "industry")
@Slf4j
@Api(tags = "行业相关接口")
public class IndustryController extends SystemController<Industry,IndustryDTO> {
    private IndustryService industryService;
    @Autowired
    private IndustryMapper industryMapper;

    public IndustryController(IndustryService industryService) {
        super(industryService);
        this.industryService = industryService;
    }

    @Override
    public ResponseCode<List<IndustryDTO>> list(Pageable pageable, IndustryDTO dto) {
        ResponseCode<List<IndustryDTO>> responseCode = ResponseCode.sucess();
        List<Industry> industryList = industryService.findAll();
        List<IndustryDTO> industryDTOList = industryMapper.toIndustryDTOs(industryList);
        responseCode.setData(industryService.getIndustryDtoList(industryDTOList));
        return responseCode;
    }

    @GetMapping(value = "convertToTreeList")
    public List<IndustryDTO> convertToTreeList(){
        List<Industry> industryList = industryService.findAll();
        List<IndustryDTO> industryDTOList = industryMapper.toIndustryDTOs(industryList);

        return industryService.getIndustryDtoList(industryDTOList);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Industry> industries) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        industries.stream().forEach(industry -> {
            SelectDTO selectDTO = new SelectDTO(industry.getId().toString(),industry.getName());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<IndustryDTO> convertTtoU(List<Industry> industries) {
        return industryMapper.toIndustryDTOs(industries);
    }

    @Override
    public IndustryDTO convertTtoU(Industry industry) {
        return industryMapper.toIndustryDTO(industry);
    }

    @Override
    public Industry convertUtoT(IndustryDTO industryDTO) {
        return industryMapper.toIndustry(industryDTO);
    }

    /**
     * 获取所有的行业列表，用户下拉框
     * @return
     */
    @GetMapping(value = "getIndustryList")
    public ResponseCode<List<SelectDTO>> getIndustryList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Industry> industryList = industryService.findAll();
        List<SelectDTO> selectDTOList = new ArrayList<>();
        industryList.stream().forEach(industry -> {
            SelectDTO selectDTO = new SelectDTO(industry.getId().toString(),industry.getName());
            selectDTOList.add(selectDTO);
        });
        responseCode.setData(selectDTOList);
        return responseCode;
    }
}
