package org.polytech.pfe.domego.generator.intermediate;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.awt.*;

public class DefineSpecificationProject {

    private final static Point positionTimeOfProject = new Point(0,1);
    private final static Point positionPriceOfProject = new Point(1,1);
    private Sheet sheet;
    private Workbook workbook;
    private int time;
    private int cost;

    public DefineSpecificationProject(Workbook workbook, Sheet sheet, int time, int cost) {
        this.workbook = workbook;
        this.sheet = sheet;
        this.time = time;
        this.cost = 10000 * cost;

    }

    public void generateSheetForThisGame(){
        sheet.getRow(positionTimeOfProject.x).getCell(positionTimeOfProject.y).setCellValue(time);
        sheet.getRow(positionPriceOfProject.x).getCell(positionPriceOfProject.y).setCellValue(cost);
        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
    }
}
