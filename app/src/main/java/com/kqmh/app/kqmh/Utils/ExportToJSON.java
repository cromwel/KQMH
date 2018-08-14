package com.kqmh.app.kqmh.Utils;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class ExportToJSON {

   /*

    .headers on
    .mode csv
    .output datavalueset.json
        INSERT TA4FU3zu93l
        SELECT customerid, firstname, lastname, company
        FROM customers;
    .quit


    curl -d @datavalueset.json "https://kqmh.uonbi.ac.ke/api/26/dataValueSets"
            -H "Content-Type:application/json" -u admin:district -v

            */






    /*

    function exportToJson() {

        mysql_connect("localhost", "root", "");
        mysql_select_db("krasimir_benchmark");

        $res = mysql_query("SELECT * FROM users ORDER BY id");
        $records = array();
        while($obj = mysql_fetch_object($res)) {
            $records []= $obj;
        }
        file_put_contents("data.json", json_encode($records));

    }*/
}
