package com.example.reservation.controller;

import com.example.reservation.constant.Method;
import com.example.reservation.domain.CommonDTO;
import com.example.reservation.domain.FitDTO;
import com.example.reservation.domain.FitFileDTO;
import com.example.reservation.service.FitService;
import com.example.reservation.utils.UiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fit")
public class FitController extends UiUtils {

    @Autowired
    private FitService fitService;

    @GetMapping("/registerForm")
    public String fitRegisterForm(@RequestParam(value = "num", required = false) Long num,
                                  Model model){
        if(num == null) {
            FitDTO fitDTO = new FitDTO();
            fitDTO.setIndoorOutdoor("실내");
            model.addAttribute("fitDTO", fitDTO);
        }
        else{
            FitDTO fitDTO= fitService.fitInfo(num);
            model.addAttribute("fitDTO", fitDTO);
        }
        return "fit/register";
    }

    @PostMapping("/register")
    public String fitRegister(@ModelAttribute FitDTO fitDTO, Model model){

        try{
            System.out.println("받은 fit 정보 : " + fitDTO);
            boolean registerResult = fitService.registerFit(fitDTO);
            if(registerResult==false){
                return showMessageWithRedirect("등록에 실패했습니다.", "/fit/registerForm", Method.GET, null, model);
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            return showMessageWithRedirect("데이터 처리 과정에 문제가 발생했습니다.", "/fit/registerForm", Method.GET, null, model);
        }catch (Exception e){
            e.printStackTrace();
            return showMessageWithRedirect("시스템에 문제가 발생했습니다.", "/fit/registerForm", Method.GET, null, model);
        }
        return showMessageWithRedirect("등록에 성공했습니다.", "/home", Method.GET, null, model);
    }

    @PostMapping("/fitDelete")
    public String fitDelete(@RequestParam(value = "num", required = false)Long num, Model model){

        if(num == null){
            return showMessageWithRedirect("올바르지 않은 접근입니다.","/home", Method.GET, null, model);
        }
        try {
            boolean deleteResult = fitService.deleteFit(num);
            if(deleteResult == false){
                return showMessageWithRedirect("데이터 삭제에 실패했습니다.", "/home", Method.GET, null, model);
            }

        }catch (DataAccessException e){
            e.printStackTrace();
            return showMessageWithRedirect("데이터 처리과정 중 문제가 발생했습니다.", "/home", Method.GET, null, model);
        }catch (Exception e){
            e.printStackTrace();
            return showMessageWithRedirect("시스템 오류가 발생했습니다.", "/home", Method.GET, null, model);
        }
        return showMessageWithRedirect("데이터 삭제에 성공했습니다.", "/home", Method.GET, null, model);
    }

    @GetMapping("/fitList")
    public String fitList(Model model){

        List<FitDTO> fitDTOList = fitService.listFit();

        model.addAttribute("fitDTOList", fitDTOList);

        return "test";
    }

    @GetMapping("/fitInfo")
    public String fitInfo(@RequestParam(value = "num", required = false) Long num,
                          Model model){

        if(num==null){
            return showMessageWithRedirect("해당하는 정보를 찾을 수 없습니다.", "/home", Method.GET, null, model);
        }
        else{
            FitDTO fitDTO = fitService.fitInfo(num);
            if(fitDTO == null || fitDTO.getDeleteYn().equals('Y')){
                return showMessageWithRedirect("해당하는 정보를 찾을 수 없습니다.", "/home", Method.GET, null, model);
            }
            else {
                System.out.println("가져온 fit 정보 : " + fitDTO);
                model.addAttribute("fitDTO", fitDTO);
                if(fitDTO.getFileAttached()==1){

                    FitFileDTO fitMainFileDTO = fitService.fitMainFileInfo(fitDTO.getNum());
                    System.out.println("가져온 fit메인파일리스트정보 : " + fitMainFileDTO);
                    model.addAttribute("fitMainFileDTO", fitMainFileDTO);

                    List<FitFileDTO> fitSubFileDTOList = fitService.fitSubFileInfo(fitMainFileDTO.getNum());
                    System.out.println("가져온 fit서브파일리스트정보 : " + fitSubFileDTOList);
                    model.addAttribute("fitSubFileDTOList", fitSubFileDTOList);
                }
                return "fit/fitInfo";
            }
        }
    }


    @GetMapping("/fitSearch")
    public String firSearch(@RequestParam(value = "bigRegion") String bigRegion,
                            @RequestParam(value = "smallRegion") String smallRegion,
                            @RequestParam(value = "typeChoice") String typeChoice,
                            Model model){

        System.out.println("bigRegion : " + bigRegion + ", smallRegion : " + smallRegion + ", typeChoice : " + typeChoice);
        return "test2";
    }
}