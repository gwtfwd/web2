package egovframework.example.drink.web;

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

import egovframework.example.drink.service.DrinkDefaultVO;
import egovframework.example.drink.service.DrinkService;
import egovframework.example.drink.service.DrinkVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class DrinkController {

	/** EgovSampleService */
	@Resource(name = "drinkService")
	private DrinkService drinkService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/**
	 * 글 목록 조회 (paging)
	 * @param findVO - 조회할 정보가 담긴 DrinkDefaultVO
	 * @param model
	 * @return "drinkList"
	 * @exception Exception
	 */
	@RequestMapping(value="/drinkList.do")
	public String selectDrinkList(@ModelAttribute("findVO") DrinkDefaultVO findVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		findVO.setPageUnit(propertiesService.getInt("pageUnit"));
		findVO.setPageSize(propertiesService.getInt("pageSize"));

		/** paging setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(findVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(findVO.getPageUnit());
		paginationInfo.setPageSize(findVO.getPageSize());

		findVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		findVO.setLastIndex(paginationInfo.getLastRecordIndex());
		findVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> drinkList = drinkService.selectDrinkList(findVO);
		model.addAttribute("resultList", drinkList);

		int totCnt = drinkService.selectDrinkListTotCnt(findVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "drink/drinkList";
	}
	
	/**
	 * 글 등록 화면 조회
	 * @param findVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "drinkRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addDrink.do", method = RequestMethod.GET)
	public String addDrinkView(@ModelAttribute("findVO") DrinkDefaultVO findVO, Model model) throws Exception {
		model.addAttribute("drinkVO", new DrinkVO());
		return "drink/drinkRegister";
	}

	/**
	 * 글 등록
	 * @param DrinkVO - 등록할 정보가 담긴 VO
	 * @param findVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/drinkList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addDrink.do", method = RequestMethod.POST)
	public String addDrink(@ModelAttribute("findVO") DrinkDefaultVO findVO, DrinkVO drinkVO, BindingResult bindingResult, Model model, SessionStatus status)	throws Exception {

		// Server-Side Validation
		beanValidator.validate(drinkVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("drinkVO", drinkVO);
			return "drink/drinkRegister";
		}

		drinkService.insertDrink(drinkVO);
		status.setComplete();
		return "forward:/drinkList.do";
	}

	/**
	 * 글 조회
	 * @param drinkVO - 조회할 정보가 담긴 VO
	 * @param findVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("drinkVO") - 조회한 정보
	 * @exception Exception
	 */
	public DrinkVO selectDrink(DrinkVO drinkVO, @ModelAttribute("findVO") DrinkDefaultVO findVO) throws Exception {
		return drinkService.selectDrink(drinkVO);
	}

	
	/**
	 * 글 수정화면 조회
	 * @param id - 수정할 글 id
	 * @param findVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "drinkRegister"
	 * @exception Exception
	 */
	@RequestMapping("/updateDrinkView.do")
	public String updateDrinkView(@RequestParam("selectedId") String id, @ModelAttribute("findVO") DrinkDefaultVO findVO, Model model) throws Exception {
		DrinkVO drinkVO = new DrinkVO();
		drinkVO.setId(id);
		// 변수명은 CoC 에 따라 drinkVO
		model.addAttribute(selectDrink(drinkVO, findVO));
		return "drink/drinkRegister";
	}

	
	/**
	 * 글 수정
	 * @param drinkVO - 수정할 정보가 담긴 VO
	 * @param findVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/drinkRegister.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateDrink.do")
	public String updateDrink(@ModelAttribute("findVO") DrinkDefaultVO findVO, DrinkVO drinkVO, BindingResult bindingResult, Model model, SessionStatus status)	throws Exception {

		beanValidator.validate(drinkVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("drinkVO", drinkVO);
			return "drink/drinkRegister";
		}

		drinkService.updateDrink(drinkVO);
		status.setComplete();
		return "forward:/drinkList.do";
	}

	/**
	 * 글 삭제
	 * @param drinkVO - 삭제할 정보가 담긴 VO
	 * @param findVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/drinkList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteDrink.do")
	public String deleteDrink(DrinkVO drinkVO, @ModelAttribute("findVO") DrinkDefaultVO findVO, SessionStatus status) throws Exception {
		drinkService.deleteDrink(drinkVO);
		status.setComplete();
		return "forward:/drinkList.do";
	}
	
	
	
	
	
	
	
}











