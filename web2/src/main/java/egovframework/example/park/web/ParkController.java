package egovframework.example.park.web;

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
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ParkController {

	/** EgovSampleService */
	@Resource(name = "parkService")
	private ParkService parkService;
	
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
	 * @return "parkList"
	 * @exception Exception
	 */
	@RequestMapping(value="/parkList.do")
	public String selectParkList(@ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, ModelMap model) throws Exception {

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

		List<?> parkList = parkService.selectParkList(parkSearchVO);
		model.addAttribute("resultList", parkList);

		int totCnt = parkService.selectParkListTotCnt(parkSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "park/parkList";
	}
	
	/**
	 * 글 등록 화면 조회
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "parkRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addPark.do", method = RequestMethod.GET)
	public String addParkView(@ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, Model model) throws Exception {
		
		List<?> parkTypeList = parkTypeService.selectParkTypeList(parkSearchVO);
		model.addAttribute("parkTypeList", parkTypeList);
		
		model.addAttribute("parkVO", new ParkVO());
		return "park/parkRegister";
	}

	/**
	 * 글 등록
	 * @param parkVO - 등록할 정보가 담긴 VO
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/parkList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addPark.do", method = RequestMethod.POST)
	public String addPark(@ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, ParkVO parkVO, BindingResult bindingResult, Model model, SessionStatus status)	throws Exception {

		// Server-Side Validation
		beanValidator.validate(parkVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("parkVO", parkVO);
			return "park/parkRegister";
		}

		parkService.insertPark(parkVO);
		status.setComplete();
		return "forward:/parkList.do";
	}

	/**
	 * 글 조회
	 * @param parkVO - 조회할 정보가 담긴 VO
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("parkVO") - 조회한 정보
	 * @exception Exception
	 */
	public ParkVO selectPark(ParkVO parkVO, @ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO) throws Exception {
		
		return parkService.selectPark(parkVO);
	}

	
	/**
	 * 글 수정화면 조회
	 * @param code - 수정할 글 code
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "parkRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateParkView.do")
	public String updateParkView(@RequestParam("selectedId") Integer id, @ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, Model model) throws Exception {
		
		List<?> parkTypeList = parkTypeService.selectParkTypeList(parkSearchVO);
		model.addAttribute("parkTypeList", parkTypeList);
		
		ParkVO parkVO = new ParkVO();
		parkVO.setId(id);
		// 변수명은 CoC 에 따라 parkVO
		model.addAttribute(selectPark(parkVO, parkSearchVO));
		return "park/parkRegister";
	}

	
	/**
	 * 글 수정
	 * @param parkVO - 수정할 정보가 담긴 VO
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/parkRegister.do"
	 * @exception Exception
	 */
	@RequestMapping("/updatePark.do")
	public String updatePark(@ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, ParkVO parkVO, BindingResult bindingResult, Model model, SessionStatus status)	throws Exception {

		beanValidator.validate(parkVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("parkVO", parkVO);
			return "park/parkRegister";
		}

		parkService.updatePark(parkVO);
		status.setComplete();
		return "forward:/parkList.do";
	}

	/**
	 * 글 삭제
	 * @param parkVO - 삭제할 정보가 담긴 VO
	 * @param parkSearchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/parkList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deletePark.do")
	public String deletePark(ParkVO parkVO, @ModelAttribute("parkSearchVO") ParkSearchVO parkSearchVO, SessionStatus status) throws Exception {
		parkService.deletePark(parkVO);
		status.setComplete();
		return "forward:/parkList.do";
	}
	
}


