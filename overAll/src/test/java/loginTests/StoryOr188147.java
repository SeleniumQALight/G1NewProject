//package com.frontier.ta.steps.ui.equity.apsl;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.assertj.core.api.Assertions;
//import org.assertj.core.api.SoftAssertions;
//import org.openqa.selenium.Keys;
//
//import com.frontier.ta.compare.ComparableMap;
//import com.frontier.ta.compare.CompareUtils;
//import com.frontier.ta.exceptions.TestExecutionException;
//import com.frontier.ta.steps.rest.main.CPSLFPRestMainSteps;
//import com.frontier.ta.steps.ui.equity.CommonGreenScreenEquitySteps;
//import com.frontier.ta.steps.ui.equity.generic.GenericTableSteps;
//import com.frontier.ta.utils.AssertMessages;
//import com.frontier.ta.web.elements.grid.GridColor;
//import com.frontier.ta.web.pages.grid.equity.cpslfp.ChangePromoStockLevelsForProductScreen;
//import com.frontier.ta.web.pages.grid.equity.cpslfp.ChangePromoStockLevelsForProductTableRowElement;
//import com.frontier.ta.web.utils.compare.GridElementProperties;
//import com.frontier.ta.web.utils.compare.GridElementsCompareUtils;
//
//import net.thucydides.core.annotations.Step;
//import net.thucydides.core.annotations.Steps;
//
//public class ChangePromoStockLevelsForProductScreenSteps extends CommonGreenScreenEquitySteps {
//
//    private static final String PROMO_PRICE = "promoPrice";
//    private static final String PROMO_COST = "promoCost";
//    private static final String PRE_DEAL_QTY = "preDealQuantity";
//    private static final String POST_DEAL_QTY = "postDealQuantity";
//    private static final String EVENT_QTY = "eventQuantity";
//    private static final String START_DEAL_STOCK_LEVEL = "startDealStockLevel";
//    private static final String END_DEAL_STOCK_LEVEL = "endDealStockLevel";
//    private static final String END_SL_MODIFIED = "endSLModified";
//    private static final String ONE_WEEK_DEMAND = "oneWeekDemand";
//    private static final String DEAL_START_WEEK = "dealStartWeek";
//    private static final String DEAL_NUMBER = "dealNumber";
//    private static final String DEAL_PROMOTION_YEAR = "dealPromotionYear";
//    private static final String BONUS_AWARD = "bonusAward";
//    private static final String PRE_VALUE = "preValue";
//    private static final String POST_VALUE = "postValue";
//    private static final String EVENT_VALUE = "eventValue";
//    private static final String STARTSL_VALUE = "startSLValue";
//    private static final String ENDSL_VALUE = "endSLValue";
//    private static final String ONEWEEKD_VALUE = "oneWeekDemandValue";
//    private static final String ZERO = "0";
//    private static final int INT_ZERO = 0;
//    private static final int MAX_COUNT = 10;
//
//    private transient ChangePromoStockLevelsForProductScreen changePromoStockLevelsForProductScreen;
//
//    @Steps
//    private CPSLFPRestMainSteps cpslfpRestMainSteps;
//    @Steps
//    private GenericTableSteps genericTableSteps;
//
//    @Step
//    public void checkDealListBasedOnRestResponse() {
//        final String dealStartWeekFormat = "(^\\d{1,2})(\\d{1}$)";
//        final String dealStartWeekFormatted = "$1/$2";
//        final String priceWithLeadingZero = "0.\\d{2}";
//        final String priceWithLeadingZeroFormat = "^0(.\\d{2}$)";
//        final String priceWithoutLeadingZeroFormatted = "$1";
//
//        List<ComparableMap> expectedRows = cpslfpRestMainSteps.getSubFileRecordsFromJson().stream()
//                .map(row -> row.exclude("LCLWrkField1NBR", "protectPreQty", "protectPostEvtQty", "protectEventQty",
//                        "protectStartSLQty", "protectEndSLQty", "protectSDDmnd", "errorMessage"))
//                .collect(Collectors.toList());
//
//        expectedRows.forEach(map -> {
//            map.applyFunctionOnKey(DEAL_START_WEEK,
//                    v -> v.replace(" ", "").replaceAll(dealStartWeekFormat, dealStartWeekFormatted));
//            map.applyFunctionOnKey(new String[] { PROMO_PRICE, PROMO_COST },
//                    v -> (v.matches(priceWithLeadingZero))
//                            ? v.replaceAll(priceWithLeadingZeroFormat, priceWithoutLeadingZeroFormatted)
//                            : v);
//            map.applyFunctionOnKey(new String[] { PRE_DEAL_QTY, POST_DEAL_QTY, EVENT_QTY, START_DEAL_STOCK_LEVEL,
//                    END_DEAL_STOCK_LEVEL, END_SL_MODIFIED, ONE_WEEK_DEMAND, }, v -> (v.equals(ZERO)) ? "" : v);
//            map.applyFunctionOnKey(BONUS_AWARD, v -> v.replace(" ", ""));
//        });
//        List<ComparableMap> actualRows = getActualRows();
//        Assertions.assertThat(actualRows).as(AssertMessages.MAPS_NOT_MATCHED).isEqualTo(expectedRows);
//    }
//
//    @Step
//    public void checkBonusLabelsVisibleAndBonusAwardsEqualToValues(final List<Map<String, String>> expectedValues) {
//        List<ComparableMap> actualValues = getActualRowElements();
//
//        assertThat(actualValues.size()).as("Related deals amount" + IS_NOT_MATCHED).isEqualTo(expectedValues.size());
//        actualValues.stream().forEach(actualRow -> {
//            Map<String, String> expectedRow = expectedValues.stream()
//                    .filter(row -> row.get(DEAL_NUMBER).equals(actualRow.get("dealNumber"))
//                            && row.get(DEAL_PROMOTION_YEAR).equals(actualRow.get("dealPromotionYear")))
//                    .findFirst().orElse(null);
//            assertThat(expectedRow).as("Expected row was not found").isNotNull();
//            assertThat(actualRow.get("bonusAward")).as("Bonus award" + IS_NOT_MATCHED)
//                    .isEqualTo(expectedRow.get(BONUS_AWARD));
//            assertThat(actualRow.get("bonusAwardLabel")).as("Bonus label" + IS_NOT_MATCHED)
//                    .isEqualTo("".equals(expectedRow.get(BONUS_AWARD)) ? "" : "B:");
//        });
//    }
//
//    private boolean isPlusSignVisible() {
//        return changePromoStockLevelsForProductScreen.getPlusSign().getText().contains("+");
//    }
//
//    private List<ComparableMap> getActualRowElements() {
//
//        boolean nextRowsBlockContainsValues = changePromoStockLevelsForProductScreen.getDealsTable().stream()
//                .anyMatch(row -> !row.getDealNumber().getText().isEmpty());
//
//        int pgDnCounter = 0;
//        List<ComparableMap> actualRowMaps = new ArrayList<>();
//
//        while (nextRowsBlockContainsValues) {
//            if (!actualRowMaps.isEmpty()) {
//                if (!actualRowMaps.get(0).get("dealNumber").equals(
//                        changePromoStockLevelsForProductScreen.getDealsTable().get(0).getDealNumber().getText())) {
//                    actualRowMaps
//                            .addAll(GridElementsCompareUtils.getComparableMaps(changePromoStockLevelsForProductScreen
//                                    .getDealsTable().stream().filter(row -> !"".equals(row.getDealNumber().getText()))
//                                    .collect(Collectors.toList()), GridElementProperties.TEXT));
//                }
//            } else {
//                actualRowMaps.addAll(GridElementsCompareUtils.getComparableMaps(
//                        changePromoStockLevelsForProductScreen.getDealsTable().stream()
//                                .filter(row -> !"".equals(row.getDealNumber().getText())).collect(Collectors.toList()),
//                        GridElementProperties.TEXT));
//            }
//            if (isPlusSignVisible() && pgDnCounter < MAX_COUNT && changePromoStockLevelsForProductScreen.getDealsTable()
//                    .stream().filter(row -> !"".equals(row.getDealNumber().getText())).count() == 6) {
//                pgDnCounter++;
//                pressPageDownButton(1);
//            } else {
//                nextRowsBlockContainsValues = false;
//            }
//        }
//        pressPageUpButton(pgDnCounter);
//        return actualRowMaps;
//    }
//
//    private List<ComparableMap> getActualRows() {
//        List<ComparableMap> actualRows = getActualRowElements();
//        actualRows = actualRows.stream()
//                .map(row -> row.exclude("optionField", "flyerPageNumberLabel", "advertisingCodeLabel",
//                        "illustrationCodeLabel", "bonusAwardLabel", "dealPeriodAndStockLevelStatusLabel"))
//                .collect(Collectors.toList());
//        return actualRows;
//    }
//
//    @Step
//    public void checkInputFieldsDisabled(final String dealNumber) {
//        List<ChangePromoStockLevelsForProductTableRowElement> dealsList = changePromoStockLevelsForProductScreen
//                .getDealsTable();
//
//        dealsList.stream().filter(row -> row.getDealNumber().getText().equals(dealNumber)).forEach(row -> {
//            assertThat(row.getOptionField().isEditable()).as(AssertMessages.isNotEditable("O field")).isTrue();
//            assertThat(row.getPreDealQuantity().isEditable()).as(AssertMessages.isNotEditable("Pre field")).isTrue();
//            assertThat(row.getPostDealQuantity().isEditable()).as(AssertMessages.isNotEditable("Post field")).isTrue();
//            assertThat(row.getEventQuantity().isEditable()).as(AssertMessages.isNotEditable("Event field")).isTrue();
//            assertThat(row.getStartDealStockLevel().isEditable()).as(AssertMessages.isNotEditable("StartSL field")).isTrue();
//            assertThat(row.getEndDealStockLevel().isEditable()).as(AssertMessages.isNotEditable("EndSL field")).isTrue();
//            assertThat(row.getOneWeekDemand().isEditable()).as(AssertMessages.isNotEditable("1WkDm field")).isTrue();
//        });
//    }
//
//    @Step
//    public void checkDealFieldHighlighted(final String dealNumber) {
//        List<ChangePromoStockLevelsForProductTableRowElement> dealsList = changePromoStockLevelsForProductScreen
//                .getDealsTable();
//
//        dealsList.stream().filter(row -> row.getDealNumber().getText().equals(dealNumber))
//                .forEach(row -> assertThat(row.getDealNumber().getBackgroundColor().equals(GridColor.GREEN))
//                        .as(AssertMessages.backgroundColorMismatch("Deal number color")));
//    }
//
//    @Step
//    public void checkDealsListSorting(final List<Map<String, String>> expectedValues) {
//        List<ComparableMap> actualDeals = getActualDealsNumbers();
//        final List<ComparableMap> expectedDeals = CompareUtils.getComparableMapsFromMapsExclude(expectedValues,
//                "promoDealPeriod", "postDealQuantity", "eventQuantity", "startDealStockLevel", "dealPromotionYear",
//                "preDealQuantity", "illustrationCode", "classification", "promoStockLevelStatus", "promoPrice",
//                "bonusAward", "oneWeekDemand", "endDealStockLevel", "advertisingCode", "endSLModified", "promoCost",
//                "dealStartWeek", "flyerPageNumber", "protectPreQty", "protectPostEvtQty", "protectEventQty",
//                "protectStartSLQty", "protectEndSLQty", "protectSDDmnd", "LCLWrkField1NBR", "errorMessage");
//
//        Assertions.assertThat(actualDeals).as(AssertMessages.MAPS_NOT_MATCHED).isEqualTo(expectedDeals);
//    }
//
//    private List<ComparableMap> getActualDealsNumbers() {
//        List<ComparableMap> actualDeals = getActualRowElements();
//        actualDeals = actualDeals.stream()
//                .map(row -> row.exclude("optionField", "dealPromotionYear", "dealStartWeek", "preDealQuantity",
//                        "postDealQuantity", "eventQuantity", "startDealStockLevel", "endDealStockLevel",
//                        "endSLModified", "oneWeekDemand", "classification", "promoPrice", "promoCost",
//                        "flyerPageNumber", "advertisingCode", "illustrationCode", "bonusAward", "promoDealPeriod",
//                        "promoStockLevelStatus", "flyerPageNumberLabel", "advertisingCodeLabel",
//                        "illustrationCodeLabel", "bonusAwardLabel", "dealPeriodAndStockLevelStatusLabel"))
//                .collect(Collectors.toList());
//        return actualDeals;
//    }
//
//    @Step
//    public void checkCursorPosition(final String dealNumber, final List<Map<String, String>> expectedValues) {
//        Map<String, String> expectedRow;
//        ChangePromoStockLevelsForProductTableRowElement actualRow;
//        List<ChangePromoStockLevelsForProductTableRowElement> actualRows;
//        List<String> actualDeals = getActualDealsNumbers().stream().map(row -> row.get(DEAL_NUMBER))
//                .collect(Collectors.toList());
//
//        if (actualDeals.isEmpty()) {
//            Assertions.fail("List of deals is empty on 'CPSLFP' screen for deal " + dealNumber);
//        }
//
//        String baseDeal = actualDeals.stream().filter(deal -> deal.equals(dealNumber)).findFirst()
//                .orElseThrow(() -> new TestExecutionException("Base Deal was not found in deals subfile"));
//
//        int baseDealIndex = actualDeals.indexOf(baseDeal);
//
//        if (actualDeals.size() > 6) {
//            pressKeyCountTimes("PAGE_UP", (actualDeals.size() / 6));
//        }
//
//        if (actualDeals.size() > 1) {
//            if (baseDealIndex >= 6) {
//                pressKeyCountTimes("PAGE_DOWN", (baseDealIndex / 6));
//            }
//
//            actualRows = changePromoStockLevelsForProductScreen.getDealsTable().stream()
//                    .filter(rowLine -> !rowLine.getDealNumber().getText().isEmpty()).collect(Collectors.toList());
//
//            if (baseDealIndex == 0) {
//                actualRow = actualRows.get(1);
//                expectedRow = expectedValues.stream()
//                        .filter(row -> row.get(DEAL_NUMBER).equals(actualRow.getDealNumber().getText())).findFirst()
//                        .orElseThrow(() -> new TestExecutionException("Expected Deal was not found in deals subfile"));
//            } else {
//                actualRow = actualRows.get(baseDealIndex - (baseDealIndex / 6) - 1);
//                expectedRow = expectedValues.stream()
//                        .filter(row -> row.get(DEAL_NUMBER).equals(actualRow.getDealNumber().getText())).findFirst()
//                        .orElseThrow(() -> new TestExecutionException("Expected Deal was not found in deals subfile"));
//            }
//
//            switch (expectedRow.get("LCLWrkField1NBR")) {
//                case "3" -> Assertions.assertThat(actualRow.getEventQuantity().hasInputCursor())
//                        .as(AssertMessages.CURSOR_POSITION_ERROR).isTrue();
//                case "4" -> Assertions.assertThat(actualRow.getStartDealStockLevel().hasInputCursor())
//                        .as(AssertMessages.CURSOR_POSITION_ERROR).isTrue();
//                case "5" -> Assertions.assertThat(actualRow.getEndDealStockLevel().hasInputCursor())
//                        .as(AssertMessages.CURSOR_POSITION_ERROR).isTrue();
//                default -> Assertions.fail("LCLWrkField1NBR was not set correct for deal " + dealNumber);
//            }
//
//        } else {
//            assertThat(changePromoStockLevelsForProductScreen.getCursorLocation().getRow())
//                    .as("Cursor location row" + IS_NOT_MATCHED).isEqualTo(0);
//            assertThat(changePromoStockLevelsForProductScreen.getCursorLocation().getColumn())
//                    .as("Cursor location column" + IS_NOT_MATCHED).isEqualTo(0);
//        }
//    }
//
//    @Step
//    public void setValuesToInputFieldsForDeal(final String dealNumber, final List<Map<String, String>> inputData) {
//        List<ChangePromoStockLevelsForProductTableRowElement> dealsList = changePromoStockLevelsForProductScreen
//                .getDealsTable();
//
//        dealsList.stream().filter(dealRow -> dealRow.getDealNumber().getText().equals(dealNumber)).limit(1)
//                .forEach(dealRow -> typeValuesToRow(dealRow, inputData));
//    }
//
//    @Step
//    public void checkStrSLValueIsEqualToTheSumOfPrePostAndEvent(final String dealNumber, final String dealStartWeek) {
//        ChangePromoStockLevelsForProductTableRowElement row = searchRow(dealNumber, dealStartWeek);
//        int expectedStrSl;
//        int actualStrSl;
//        expectedStrSl = convertStringToInt(row.getPreDealQuantity().getText())
//                + convertStringToInt(row.getPostDealQuantity().getText())
//                + convertStringToInt(row.getEventQuantity().getText());
//        actualStrSl = convertStringToInt(row.getStartDealStockLevel().getText());
//        assertThat(actualStrSl).as("StrSL was calculated not correct").isEqualTo(expectedStrSl);
//    }
//
//    private int convertStringToInt(final String value) {
//        String emptyStr = "";
//        return value.equals(emptyStr) ? INT_ZERO : Integer.parseInt(value);
//    }
//
//    private ChangePromoStockLevelsForProductTableRowElement searchRow(final String dealNumber,
//                                                                      final String dealStartWeek) {
//        ChangePromoStockLevelsForProductTableRowElement resultRow;
//        for (int counter = 0; counter < MAX_COUNT
//                || changePromoStockLevelsForProductScreen.getPlusSign().isVisible(); counter++) {
//            for (ChangePromoStockLevelsForProductTableRowElement row : changePromoStockLevelsForProductScreen
//                    .getDealsTable()) {
//                if (row.getDealNumber().getText().equals(dealNumber)
//                        && row.getDealStartWeek().getText().equals(dealStartWeek)) {
//                    resultRow = row;
//                    return resultRow;
//                }
//            }
//            pressNonAlphanumericKey(Keys.PAGE_DOWN);
//        }
//        throw new TestExecutionException("Deal in subfile was not found");
//    }
//
//    @Step
//    public void verifyInputFieldsValuesForDeal(final String dealNumber, final List<Map<String, String>> data) {
//        List<ChangePromoStockLevelsForProductTableRowElement> dealsList = changePromoStockLevelsForProductScreen
//                .getDealsTable();
//
//        SoftAssertions softAssertions = new SoftAssertions();
//        dealsList.stream().filter(row -> row.getDealNumber().getText().equals(dealNumber)).forEach(row -> {
//            softAssertions.assertThat(row.getPreDealQuantity().getText()).as("Pre value" + IS_NOT_MATCHED)
//                    .isEqualTo(data.stream().filter(dataRow -> dataRow.get(DEAL_NUMBER).equals(dealNumber)).findFirst()
//                            .orElseThrow().get("preDealQuantity"));
//            softAssertions.assertThat(row.getPostDealQuantity().getText()).as("Post value" + IS_NOT_MATCHED)
//                    .isEqualTo(data.stream().filter(dataRow -> dataRow.get(DEAL_NUMBER).equals(dealNumber)).findFirst()
//                            .orElseThrow().get("postDealQuantity"));
//            softAssertions.assertThat(row.getEventQuantity().getText()).as("Event value" + IS_NOT_MATCHED)
//                    .isEqualTo(data.stream().filter(dataRow -> dataRow.get(DEAL_NUMBER).equals(dealNumber)).findFirst()
//                            .orElseThrow().get("eventQuantity"));
//            softAssertions.assertThat(row.getStartDealStockLevel().getText()).as("StartSL value" + IS_NOT_MATCHED)
//                    .isEqualTo(data.stream().filter(dataRow -> dataRow.get(DEAL_NUMBER).equals(dealNumber)).findFirst()
//                            .orElseThrow().get("startDealStockLevel"));
//            softAssertions.assertThat(row.getEndDealStockLevel().getText()).as("EndSL value" + IS_NOT_MATCHED)
//                    .isEqualTo(data.stream().filter(dataRow -> dataRow.get(DEAL_NUMBER).equals(dealNumber)).findFirst()
//                            .orElseThrow().get("endDealStockLevel"));
//            softAssertions.assertThat(row.getOneWeekDemand().getText()).as("OneWeekDemand value" + IS_NOT_MATCHED)
//                    .isEqualTo(data.stream().filter(dataRow -> dataRow.get(DEAL_NUMBER).equals(dealNumber)).findFirst()
//                            .orElseThrow().get("oneWeekDemand"));
//        });
//        softAssertions.assertAll();
//    }
//
//    private String getValueByField(final List<Map<String, String>> dataListRow, final String nameValue) {
//        return dataListRow.stream().filter(datarow -> datarow.get(KEY_FIELD).equals(nameValue)).findFirst()
//                .orElse(Map.of((KEY_VALUE), "")).get(KEY_VALUE);
//    }
//
//    private void typeIfValueNotEmpty(final List<Map<String, String>> dataListRow,
//                                     final ChangePromoStockLevelsForProductTableRowElement row, final String fieldName) {
//
//        if (!getValueByField(dataListRow, fieldName).isEmpty()) {
//
//            switch (fieldName) {
//                case PRE_VALUE -> row.getPreDealQuantity().clearAndType(getValueByField(dataListRow, fieldName));
//                case POST_VALUE -> row.getPostDealQuantity().clearAndType(getValueByField(dataListRow, fieldName));
//                case EVENT_VALUE -> row.getEventQuantity().clearAndType(getValueByField(dataListRow, fieldName));
//                case STARTSL_VALUE -> row.getStartDealStockLevel().clearAndType(getValueByField(dataListRow, fieldName));
//                case ENDSL_VALUE -> row.getEndDealStockLevel().clearAndType(getValueByField(dataListRow, fieldName));
//                case ONEWEEKD_VALUE -> row.getOneWeekDemand().clearAndType(getValueByField(dataListRow, fieldName));
//                default -> Assertions.fail("Incorrect input test data was generated for field name: " + fieldName);
//            }
//        }
//    }
//
//    private void typeValuesToRow(final ChangePromoStockLevelsForProductTableRowElement row,
//                                 final List<Map<String, String>> inputData) {
//
//        Map<String, Boolean> fieldEditable = new HashMap<>();
//        fieldEditable.put(PRE_VALUE, row.getPreDealQuantity().isEditable());
//        fieldEditable.put(POST_VALUE, row.getPostDealQuantity().isEditable());
//        fieldEditable.put(EVENT_VALUE, row.getEventQuantity().isEditable());
//        fieldEditable.put(STARTSL_VALUE, row.getStartDealStockLevel().isEditable());
//        fieldEditable.put(ENDSL_VALUE, row.getEndDealStockLevel().isEditable());
//        fieldEditable.put(ONEWEEKD_VALUE, row.getOneWeekDemand().isEditable());
//
//        for (String fieldName : new ArrayList<>(fieldEditable.keySet())) {
//            if (fieldEditable.get(fieldName)
//                    && inputData.stream().anyMatch(datarow -> datarow.get(KEY_FIELD).equals(fieldName))) {
//                typeIfValueNotEmpty(inputData, row, fieldName);
//            }
//        }
//    }
//}