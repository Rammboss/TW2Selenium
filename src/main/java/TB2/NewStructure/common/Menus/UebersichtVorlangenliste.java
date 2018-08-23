package TB2.NewStructure.common.Menus;

import TB2.TB2.Element;

public class UebersichtVorlangenliste extends AbstractMenue {

    public static Element FARM_EDIT = new Element("Farm Vorlage", "tr", "ng-repeat", "preset in data.presets", "<span class=\"preset-name\">farm</span>", Element.BY_CSS_SELECTOR_WITH_CRITERIA);


    public static Element FARM_STATUS = new Element("Farm Vorlage", "tr", "ng-repeat", "preset in data.presets", "farm", Element.BY_CSS_SELECTOR_WITH_CRITERIA);
    public static Element ERSTELLE_NEUE_VORLAGE = new Element("Erstelle Neue Vorlage", "/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a", Element.BY_XPATH);
    public static Element INPUT_NAME_DER_VORLAGE = new Element("Erstelle Neue Vorlage", "/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/input", Element.BY_XPATH);
    public static Element NEUE_VORLAGE_SPEICHERN = new Element("Erstelle Neue Vorlage", "/html/body/div[4]/div/div/div/div/footer/ul/li/a", Element.BY_XPATH);
    public static Element NEUE_VORLAGE_SPEICHERN_OK = new Element("Erstelle Neue Vorlage", "/html/body/div[4]/div/div/div/div/footer/ul/li[2]/a", Element.BY_XPATH);


//    public static Element GLOBALE_VORLAGENLISTE_STATUS = new Element("Globale Vorlagenliste Status", "/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[2]/div/div/div/div",
//            Element.BY_XPATH);
//
//    public static Element GLOBALE_VORLAGENLISTE_BEARBEITEN = new Element("Globale Vorlagenliste bearbeiten", "/html/body/div[4]/div/div/div/div/div/div[1]/div/div[2]/table/tbody/tr[2]/td[3]/a[2]",
//            Element.BY_XPATH);

    static {
        FARM_EDIT.setElement(new Element("Farmvorlage bearbeiten", "a", "class", "btn-orange size-44x44 icon-34x34-edit", Element.BY_CSS_SELECTOR));
        FARM_STATUS.setElement(new Element("Farmvorlage Status", "div", "ng-click", "toggleValue()", Element.BY_CSS_SELECTOR));
    }

    @Override
    public void openMenu() {
        // TODO Auto-generated method stub

    }

}
