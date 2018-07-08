package egovframework.example.parkType.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.example.park.service.ParkSearchVO;
import egovframework.example.park.service.ParkService;
import egovframework.example.park.service.ParkVO;
import egovframework.example.parkType.service.ParkTypeService;
import egovframework.example.parkType.service.ParkTypeVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ParkTypeController {

	/** EgovSampleService */
	@Resource(name = "parkTypeService")
	private ParkTypeService parkTypeService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/**
	 * 글 목록 조회 (paging)
	 * @param parkSearchVO - 조회할 정보가 담긴 ParkSearchVO
	 * @param model
	 * @return "parkTypeList"
	 * @exception Exception
	 */
	@RequestMapping(value="/parkTypeList.do")
	public String selectParkTypeList(@ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		parkSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		parkSearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** paging setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(parkSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(parkSearchVO.getPageUnit());
		paginationInfo.setPageSize(parkSearchVO.getPageSize());

		parkSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		parkSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		parkSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> parkTypeList = parkTypeService.selectParkTypeList(parkSearchVO);
		model.addAttribute("resultList", parkTypeList);

		int totCnt = parkTypeService.selectParkTypeListTotCnt(parkSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "parkType/parkTypeList";
	}
	
	/**
	 * 글 등록 화면 조회
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "parkTypeRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addParkType.do", method = RequestMethod.GET)
	public String addParkTypeView(@ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, Model model) throws Exception {
		model.addAttribute("parkTypeVO", new ParkTypeVO());
		
		System.out.println("//////////////////////hgfjhgfj///////////");
		return "parkType/parkTypeRegister";
	}

	/**
	 * 글 등록
	 * @param parkTypeVO - 등록할 정보가 담긴 VO
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/parkTypeList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addParkType.do", method = RequestMethod.POST)
	public String addParkType(@ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, ParkTypeVO parkTypeVO, BindingResult bindingResult, Model model, SessionStatus status)	throws Exception {

		// Server-Side Validation
		beanValidator.validate(parkTypeVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("parkTypeVO", parkTypeVO);
			return "parkType/parkTypeRegister";
		}

		parkTypeService.insertParkType(parkTypeVO);
		status.setComplete();
		return "forward:/parkTypeList.do";
	}

	
	/**
	 * 글 조회
	 * @param parkTypeVO - 조회할 정보가 담긴 VO
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("parkTypeVO") - 조회한 정보
	 * @exception Exception
	 */
	public ParkTypeVO selectParkType(ParkTypeVO parkTypeVO, @ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO) throws Exception {
		return parkTypeService.selectParkType(parkTypeVO);
	}

	
	/**
	 * 글 수정화면 조회
	 * @param code - 수정할 글 code
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "parkTypeRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateParkTypeView.do")
	public String updateParkTypeView(@RequestParam("selectedCode") String code, @ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, Model model) throws Exception {
		ParkTypeVO parkTypeVO = new ParkTypeVO();
		parkTypeVO.setCode(code);
		// 변수명은 CoC 에 따라 parkVO
		model.addAttribute(selectParkType(parkTypeVO, parkSearchVO));
		return "parkType/parkTypeRegister";
	}

	/**
	 * 글 수정
	 * @param parkTypeVO - 수정할 정보가 담긴 VO
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/parkTypeRegister.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateParkType.do")
	public String updateParkType(@ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, ParkTypeVO parkTypeVO, BindingResult bindingResult, Model model, SessionStatus status)	throws Exception {

		beanValidator.validate(parkTypeVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("parkTypeVO", parkTypeVO);
			return "parkType/parkTypeRegister";
		}

		parkTypeService.updateParkType(parkTypeVO);
		status.setComplete();
		return "forward:/parkTypeList.do";
	}

	/**
	 * 글 삭제
	 * @param parkTypeVO - 삭제할 정보가 담긴 VO
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/parkTypeList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteParkType.do")
	public String deleteParkType(ParkTypeVO parkTypeVO, @ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, SessionStatus status) throws Exception {
		parkTypeService.deleteParkType(parkTypeVO);
		status.setComplete();
		return "forward:/parkTypeList.do";
	}
	
	
}
