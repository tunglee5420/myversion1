package com.just.myproject.Service.Imp;

import com.just.myproject.Entity.Classtable;
import com.just.myproject.Entity.ErrorRecord;
import com.just.myproject.Mapper.AdminMapper;
import com.just.myproject.Service.AdminService;
import com.just.myproject.Utils.MyException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    public boolean fileParse(MultipartFile file){
        List<Classtable> clist=this.importExcel(file);
        if (clist!=null){
            int k=adminMapper.importExcel(clist);
            return k==0?false:true;
        }
        return false;

    }
    public boolean setUseServ(String roomnum,int s){
        if (roomnum!=null){
            return adminMapper.setUse(roomnum,s)!=0?true:false;
        }
        return false;
    }
    public boolean setAdminServ(String worknum, int k){
        if(k==2){
            return adminMapper.setAdmin(worknum)==1?true:false;
        }
        return false;
    }

    public boolean importInfo(MultipartFile file){
        List<Map<String,Object>> list=this.importCalander(file);
        if (list!=null&&!list.isEmpty()){
            int k=adminMapper.importCalander(list);
            return k==0?false:true;
        }
        return false;
    }


    public List<Classtable> importExcel(MultipartFile file){
        String filname=file.getOriginalFilename();
        System.out.println(filname);
        Workbook wb=null;
        InputStream is = null;
        List<Classtable> list=new ArrayList<>();
        try {
            is =file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(is==null) return null;
        try {
            if(filname.matches("^.+\\.(?i)(xls)$")){
                wb=new HSSFWorkbook(is);
            }else if(filname.matches("^.+\\.(?i)(xlsx)$")){
                wb = new XSSFWorkbook(is);
            }else {
                throw new MyException("文件格式不正确");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int wn=0,wd=0,co=0,cu = 0,u=0,rn=0,tm=0;
        int sheets=wb.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {
            Sheet sheet=wb.getSheetAt(i);
            if(sheet==null) return null;
            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();
            Row menue = sheet.getRow(firstRowIndex+1);
            for (int k = menue.getFirstCellNum(); k <menue.getLastCellNum(); k++) {
                Cell cell=menue.getCell(k);
                Object object= this.getCellValue(cell);
                if(cell!=null){
                    if(object.equals("科目")){
                        cu=k;
                    }
                    if(object.equals("周号")){
                        wn=k;
                    }
                    if(object.equals("周序")){
                        wd=k;
                    }
                    if(object.equals("节序")){
                        co=k;
                    }
                    if(object.equals("老师")){
                        u=k;
                    }
                    if(object.equals("教室")){
                        rn=k;
                    }
                    if(object.equals("学期")){
                        tm=k;
                    }
                }
            }
            for (int j = firstRowIndex+2; j <= lastRowIndex; j++) {
                Row currentRow = sheet.getRow(j);// 当前行
                Classtable  classtable=new Classtable();
                if(currentRow.getCell(cu)!=null&&currentRow.getCell(wd)!=null&&currentRow.getCell(co)!=null&&currentRow.getCell(rn)!=null&&currentRow.getCell(u)!=null&&currentRow.getCell(wn)!=null&&currentRow.getCell(tm)!=null){
                    classtable.setCourse(currentRow.getCell(cu).toString());
                    classtable.setWeekday(getCellValue(currentRow.getCell(wd)).toString());
                    classtable.setClassOrder(getCellValue(currentRow.getCell(co)).toString());
                    classtable.setRoomnum(currentRow.getCell(rn).toString());
                    classtable.setUser(currentRow.getCell(u).toString());
                    classtable.setWeeknum(getCellValue(currentRow.getCell(wn)).toString());
                    classtable.setTerm( currentRow.getCell(tm).toString());
                }else {
                    return null;
                }
                list.add(classtable);
            }
        }
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public  Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        Object obj = null;

        if(cell.getCellType()==Cell.CELL_TYPE_STRING){
            String value = String.valueOf(cell.getStringCellValue());
            value = value.replace(" ", "");
            value = value.replace("\n", "");
            value = value.replace("\t", "");
            obj = value;
        }else if(cell.getCellType()==Cell.CELL_TYPE_ERROR) {
            obj = cell.getErrorCellValue();
        }else if(cell.getCellType()==Cell.CELL_TYPE_FORMULA){
            try {
                obj = String.valueOf(cell.getStringCellValue());
            } catch (IllegalStateException e) {
                String valueOf = String.valueOf(cell.getNumericCellValue());
                BigDecimal bd = new BigDecimal(Double.valueOf(valueOf));
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                obj = bd;
            }
        }else if( cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
            if(HSSFDateUtil.isCellDateFormatted(cell)) {
              Date date=cell.getDateCellValue();
              SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd");
              obj=ds.format(date);
            }else {
                obj = (int)cell.getNumericCellValue();
            }

        }else {
            obj = cell.getBooleanCellValue();
        }

        return obj;
    }
    public List<Map<String,Object>> importCalander(MultipartFile file){
        String filname=file.getOriginalFilename();
        Workbook wb=null;
        InputStream is = null;
        List<Map<String,Object>> clist=new ArrayList<>();
        try {
            is =file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(is==null) return null;
        try {
            if(filname.matches("^.+\\.(?i)(xls)$")){
                wb=new HSSFWorkbook(is);
            }else if(filname.matches("^.+\\.(?i)(xlsx)$")){
                wb = new XSSFWorkbook(is);
            }else {
                throw new MyException("文件格式不正确");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int wn=0,wd=0,date=0,tm=0;
        int sheets=wb.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {
            Sheet sheet=wb.getSheetAt(i);
            if(sheet==null) return null;
            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();
            Row menue = sheet.getRow(firstRowIndex+1);
            for (int k = menue.getFirstCellNum(); k <menue.getLastCellNum(); k++) {
                Cell cell=menue.getCell(k);
                Object object= this.getCellValue(cell);
                if(cell!=null){
                    if(object.equals("日期")){
                        date=k;
                    }
                    if(object.equals("周号")){
                        wn=k;
                    }
                    if(object.equals("周次")){
                        wd=k;
                    }
                    if(object.equals("学期")){
                        tm=k;
                    }
                }

            }
            for (int j = firstRowIndex+2; j <= lastRowIndex; j++) {
                Row currentRow = sheet.getRow(j);// 当前行
                Map<String,Object> calander=new HashMap<>();
                if(currentRow.getCell(date)!=null&&currentRow.getCell(wd)!=null&&currentRow.getCell(wn)!=null&&currentRow.getCell(tm)!=null){
                    calander.put("date",getCellValue(currentRow.getCell(date)));
                    calander.put("weeknum",getCellValue(currentRow.getCell(wn)));
                    calander.put("weekday",getCellValue(currentRow.getCell(wd)).toString());
                    calander.put("term", currentRow.getCell(tm).toString());
                }
                clist.add(calander);
            }
        }
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(clist);
        return clist;
    }
    public List<Map<String, Object>> getRecord(Map<String, Object> conditonMap){
        List<Map<String,Object>>list=adminMapper.getRecords(conditonMap);
        return list;
    }
    public List<ErrorRecord> getErrorInfo(){
        return adminMapper.getErrorInfo();
    }
}
