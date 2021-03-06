package apicurito.tests.steps;

import apicurito.tests.utils.slenide.*;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;

import static com.codeborne.selenide.Condition.*;

public class PathSteps {

    private static SelenideElement PARAMETERS_SECTION = PathUtils.getPathPageRoot().$("path-params-section");

    /**
     *
     * @param operation must be convertable to operation enum. See Operations enum
     */
    @And("^create new \"([^\"]*)\" operation$")
    public void createNewOperation(String operation){
        PathUtils.getCreateOperationButton(Operations.valueOf(operation))
                .click();
    }

    @And("^select operation \"([^\"]*)\"$")
    public void selectOperation(String operation){
        PathUtils.getOperationButton(Operations.valueOf(operation), CommonUtils.getAppRoot().shouldBe(visible,enabled).shouldNotHave(attribute("disabled")))
                .click();
    }

    @And("^create path parameter \"([^\"]*)\"$")
    public void createPathParameter(String parameter) {
        PathUtils.createPathParameter(parameter);
    }

    @And("^set description \"([^\"]*)\" for path parameter \"([^\"]*)\"$")
    public void setDescriptionPathParameter(String description, String parameter) {
        PathUtils.openPathDescription(parameter);
        CommonUtils.setValueInTextArea(description, PARAMETERS_SECTION);
    }

    @And("^set path parameter type \"([^\"]*)\" for path parameter \"([^\"]*)\"$")
    public void setPathParameterTypeForPathParameter(String type, String parameter) {
        PathUtils.openPathTypes(parameter);
        SelenideElement parameterElement = PARAMETERS_SECTION.$$("path-param-row")
                .filter(text(parameter)).first();

        CommonUtils.setDropDownValue(CommonUtils.DropdownButtons.TYPE.getButtonId(), type, parameterElement);
    }

    @And("^set path parameter type of \"([^\"]*)\" for path parameter \"([^\"]*)\"$")
    public void setPathParameterTypeOfForPathParameter(String of, String parameter) {
        PathUtils.openPathTypes(parameter);
        SelenideElement parameterElement = PARAMETERS_SECTION.$$("path-param-row")
                .filter(text(parameter)).first();

        CommonUtils.setDropDownValue(CommonUtils.DropdownButtons.TYPE_OF.getButtonId(), of, parameterElement);
    }

    @And("^set path parameter type as \"([^\"]*)\" for path parameter \"([^\"]*)\"$")
    public void setPathParameterTypeAsForPathParameter(String as, String parameter) {
        PathUtils.openPathTypes(parameter);
        SelenideElement parameterElement = PARAMETERS_SECTION.$$("path-param-row")
                .filter(text(parameter)).first();

        CommonUtils.setDropDownValue(CommonUtils.DropdownButtons.TYPE_AS.getButtonId(), as, parameterElement);
    }

    public enum Operations{
        GET,
        PUT,
        POST,
        DELETE,
        OPTIONS,
        HEAD,
        PATCH
    }
}
