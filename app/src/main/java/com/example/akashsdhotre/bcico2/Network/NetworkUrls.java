package com.example.akashsdhotre.bcico2.Network;

/**
 * Created by Subhodh on 11/30/2016.
 */

public class NetworkUrls {
    public NetworkUrls() {
    }

    public static abstract class BCICOLinks {
        //public static final String BASE_URL = "https://connect-mscom.com/";
        public static final String BASE_URL="http://192.168.3.23:9000/";
       /*
        //Common URL
        public static final String LOGIN_URL="http://connect-mscom.com/Apicalls/userlogin";
        public static final String LOGOUT_URL="http://connect-mscom.com/Apicalls/userlogout";
        public static final String REFRESH_TOKEN_URL="http://connect-mscom.com/Apicalls/update_user_token";




        //Merchandiser
        public static final String M_GET_ALL_OFFLINE_URL="http://connect-mscom.com/API_Merchandiser/store_category_product_by_merchandiser";
        public static final String M_OFFLINE_BUNCH_UPLOAD_URL="http://connect-mscom.com/API_Merchandiser/bunch_store_entry_notes";
        public static final String M_REJECT_LIST_URL="http://connect-mscom.com/API_Merchandiser/reject_list";
        public static final String M_STOCK_ENTRY_URL="http://connect-mscom.com/API_Merchandiser/store_entry_notes";
        public static final String M_APPROVE_PENDING_URL="http://connect-mscom.com/API_Merchandiser/stock_approved_pending_list";
        public static final String M_REJECT_UPDATE_URL="http://connect-mscom.com/API_Merchandiser/reject_to_pending_stock_entry_notes";
        public static final String M_SYNCHRONISE_ENTRIES="http://connect-mscom.com/API_Merchandiser/sync_stock_entries_status";
        public static final String M_UPDATE_PENDING_ENTRY="http://connect-mscom.com/API_Merchandiser/edit_store_entry_notes";
        public static final String M_EXPIRY_LIST="http://connect-mscom.com/API_Merchandiser/merchandier_sku_expiry_list";



        //Promoter
        public static final String P_GET_ALL_OFFLINE_URL="http://connect-mscom.com/API_Promoter/store_category_product_by_promoter_id";
        public static final String P_STOCK_ENTRY_NOTE_URL="http://connect-mscom.com/API_Promoter/store_entry_note";
        public static final String P_APPROVE_PENDING_URL="http://connect-mscom.com/API_Promoter/stock_approved_pending_list";
        public static final String P_OFFLINE_BUNCH_UPLOAD_URL="http://connect-mscom.com/API_Promoter/bunch_store_entry_notes";
        public static final String P_REJECT_LIST_URL="http://connect-mscom.com/API_Promoter/reject_list";
        public static final String P_REJECT_UPDATE_URL="http://connect-mscom.com/API_Promoter/reject_to_pending_stock_entry_notes";
        public static final String P_SYNCHRONISE_ENTRIES="http://connect-mscom.com/API_Promoter/sync_promoter_entries_status";
        public static final String P_UPDATE_PENDING_ENTRY="http://connect-mscom.com/API_Promoter/edit_promoter_pending_entry_notes";




        //Supervisor
        public static final String S_GET_MERCHANDISER_URL="http://connect-mscom.com/API_Supervisor/merchandiser_list";
        public static final String S_GET_PROMOTER_URL="http://connect-mscom.com/API_Supervisor/promoter_list";
        public static final String S_PROMOTER_PENDING_URL="http://connect-mscom.com/API_Supervisor/promoter_pending_list_by_storeId";
        public static final String S_MERCHANDISER_PENDING_URL="http://connect-mscom.com/API_Supervisor/merchandiser_pending_list_by_storeId";
        public static final String S_PROMOTER_APPROVE_ENTRY_URL="http://connect-mscom.com/API_Supervisor/approve_reject_notes_promoter";
        public static final String S_MERCHANDISER_APPROVE_ENTRY_URL="http://connect-mscom.com/API_Supervisor/approve_reject_notes_merchandiser";

        */


        //Common URL
        public static final String LOGIN_URL = BASE_URL + "login";
        public static final String GET_POST_DATA = BASE_URL + "getPostData";
        public static final String REGISTRATION_URL = BASE_URL + "createUser";
        public static final String LOGOUT_URL = BASE_URL + "Apicalls/userlogout";
        public static final String REFRESH_TOKEN_URL = BASE_URL + "Apicalls/update_user_token";

//        public static final String LOGIN_URL = BASE_URL + "Apicalls/testuserlogin.php";
//        public static final String LOGOUT_URL = BASE_URL + "Apicalls/userlogout";
//        public static final String REFRESH_TOKEN_URL = BASE_URL + "Apicalls/update_user_token";


        //Merchandiser
        public static final String M_GET_ALL_OFFLINE_URL = BASE_URL + "API_Merchandiser/store_category_product_by_merchandiser";
        public static final String M_OFFLINE_BUNCH_UPLOAD_URL = BASE_URL + "API_Merchandiser/bunch_store_entry_notes";
        public static final String M_REJECT_LIST_URL = BASE_URL + "API_Merchandiser/reject_list";
        public static final String M_STOCK_ENTRY_URL = BASE_URL + "API_Merchandiser/store_entry_notes";
        public static final String M_APPROVE_PENDING_URL = BASE_URL + "API_Merchandiser/stock_approved_pending_list";
        public static final String M_REJECT_UPDATE_URL = BASE_URL + "API_Merchandiser/reject_to_pending_stock_entry_notes";
        public static final String M_SYNCHRONISE_ENTRIES = BASE_URL + "API_Merchandiser/sync_stock_entries_status";
        public static final String M_UPDATE_PENDING_ENTRY = BASE_URL + "API_Merchandiser/edit_store_entry_notes";
        public static final String M_EXPIRY_LIST = BASE_URL + "API_Merchandiser/merchandier_sku_expiry_list";


        //Promoter
        public static final String P_GET_ALL_OFFLINE_URL = BASE_URL + "API_Promoter/store_category_product_by_promoter_id";
        //        public static final String P_STOCK_ENTRY_NOTE_URL = BASE_URL + "API_Promoter/store_entry_note";
        public static final String P_STOCK_ENTRY_NOTE_URL = BASE_URL + "API_Promoter/new_store_entry_note";
        public static final String P_STOCK_ENTRY_VERSION_CONTROL_NOTE_URL=BASE_URL+"API_Promoter/new_store_entry_note_with_version_control";
        public static final String P_APPROVE_PENDING_URL = BASE_URL + "API_Promoter/stock_approved_pending_list";
        //        public static final String P_OFFLINE_BUNCH_UPLOAD_URL = BASE_URL + "API_Promoter/bunch_store_entry_notes";
        public static final String P_OFFLINE_BUNCH_UPLOAD_URL = BASE_URL + "API_Promoter/new_bunch_store_entry_notes";
        public static final String P_REJECT_LIST_URL = BASE_URL + "API_Promoter/reject_list";
        public static final String P_REJECT_UPDATE_URL = BASE_URL + "API_Promoter/reject_to_pending_stock_entry_notes";
        //                public static final String P_ENTRY_LIST = BASE_URL + "API_Promoter/get_promoter_entries_according_date";
        public static final String P_ENTRY_LIST = BASE_URL + "API_Promoter/new_get_promoter_entries_according_date";
        //        public static final String P_UPDATE_PENDING_ENTRY = BASE_URL + "API_Promoter/edit_promoter_pending_entry_notes";
        public static final String P_UPDATE_PENDING_ENTRY = BASE_URL + "API_Promoter/new_edit_promoter_entry_notes";
        public static final String P_CUSTOMER_UPDATE_PENDING_ENTRY=BASE_URL+ "API_Promoter/customer_new_edit_promoter_entry_notes";
        public static final String P_VERSION_CONTROL=BASE_URL+"API_Promoter/check_promoter_data_version";


        //Supervisor
        public static final String S_PROMOTER_UPDATE_DATE_PENDING_ENTRY=BASE_URL+ "API_Supervisor/edit_promoter_entry_notes_dates";//SQR_Change_1
        public static final String S_GET_MERCHANDISER_URL = BASE_URL + "API_Supervisor/merchandiser_list";
        public static final String S_GET_PROMOTER_URL = BASE_URL + "API_Supervisor/promoter_list";
        public static final String S_PROMOTER_PENDING_URL = BASE_URL + "API_Supervisor/promoter_pending_list_by_storeId";
        public static final String S_MERCHANDISER_PENDING_URL = BASE_URL + "API_Supervisor/merchandiser_pending_list_by_storeId";
        public static final String S_PROMOTER_APPROVE_ENTRY_URL = BASE_URL + "API_Supervisor/approve_reject_notes_promoter";
        public static final String S_MERCHANDISER_APPROVE_ENTRY_URL = BASE_URL + "API_Supervisor/approve_reject_notes_merchandiser";


    }
}
