package com.imaginea.android.sugarcrm.restapi;

import java.util.HashMap;
import java.util.List;

import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.imaginea.android.sugarcrm.ModuleFields;
import com.imaginea.android.sugarcrm.util.Module;
import com.imaginea.android.sugarcrm.util.ModuleField;
import com.imaginea.android.sugarcrm.util.RestUtil;
import com.imaginea.android.sugarcrm.util.SugarBean;

/**
 * LeadsApiTest, tests the rest api calls
 * 
 * @author chander
 * 
 */
public class LeadsApiTest extends RestAPITest {
    String moduleName = "Leads";

    String[] fields = new String[] {};

    String[] customFields = new String[] { "a", "b" };

    String[] selectFields = { ModuleFields.FIRST_NAME, ModuleFields.LAST_NAME, ModuleFields.EMAIL1 };

    HashMap<String, List<String>> linkNameToFieldsArray = new HashMap<String, List<String>>();

    String query = "", orderBy = "";

    int deleted = 0;

    public final static String LOG_TAG = "LeadsApiTest";

    @SmallTest
    public void testGetAllModuleFields() throws Exception {

        Module module = RestUtil.getModuleFields(url, mSessionId, moduleName, fields);
        assertNotNull(module);

        List<ModuleField> moduleFields = module.getModuleFields();
        assertNotNull(moduleFields);
        assertTrue(moduleFields.size() > 0);

        for (ModuleField moduleField : moduleFields) {
            Log.d(LOG_TAG, "name :" + moduleField.getName());
            Log.d(LOG_TAG, "label :" + moduleField.getLabel());
            Log.d(LOG_TAG, "type :" + moduleField.getType());
            Log.d(LOG_TAG, "isReuired :" + moduleField.isRequired());
            assertNotNull(moduleField);
            assertNotNull(moduleField.getName());
        }
    }

    @SmallTest
    public void testLeadsList() throws Exception {
        int offset = 0;
        int maxResults = 10;
        // String[] selectFields = new String[] {};
        SugarBean[] sBeans = getSugarBeans(offset, maxResults);
        assertNotNull(sBeans);
        assertTrue(sBeans.length > 0);

        if (Log.isLoggable(LOG_TAG, Log.DEBUG)) {
            for (SugarBean sBean : sBeans) {
                assertNotNull(sBean);
                Log.d(LOG_TAG, sBean.getBeanId());
                Log.d(LOG_TAG, sBean.getFieldValue(ModuleFields.EMAIL1));
            }
        }
    }

    /**
     * demonstrates the usage of RestUtil for leads List. ModuleFields.NAME or FULL_NAME is not
     * returned by Sugar CRM. The fields that are not returned by SugarCRM can be automated, but not
     * yet generated
     * 
     * @param offset
     * @param maxResults
     * @return
     * @throws Exception
     */
    private SugarBean[] getSugarBeans(int offset, int maxResults) throws Exception {

        SugarBean[] sBeans = RestUtil.getEntryList(url, mSessionId, moduleName, query, orderBy, offset
                                        + "", selectFields, linkNameToFieldsArray, maxResults + "", deleted
                                        + "");
        return sBeans;
    }

}
