package com.petfolio.infinitus.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.adapter.AddImageListAdapter;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.appUtils.FileUtil;
import com.petfolio.infinitus.requestpojo.AddYourPetRequest;
import com.petfolio.infinitus.requestpojo.BreedTypeRequest;
import com.petfolio.infinitus.requestpojo.DocBusInfoUploadRequest;
import com.petfolio.infinitus.requestpojo.PetDetailsRequest;
import com.petfolio.infinitus.requestpojo.SPCreateAppointmentRequest;
import com.petfolio.infinitus.responsepojo.AddYourPetResponse;
import com.petfolio.infinitus.responsepojo.BreedTypeResponse;
import com.petfolio.infinitus.responsepojo.FileUploadResponse;
import com.petfolio.infinitus.responsepojo.PetDetailsResponse;
import com.petfolio.infinitus.responsepojo.PetTypeListResponse;
import com.petfolio.infinitus.responsepojo.SPCreateAppointmentResponse;
import com.petfolio.infinitus.sessionmanager.SessionManager;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceBookAppointmentActivity extends AppCompatActivity implements PaymentResultListener {

    private static final String TAG = "ServiceBookAppointmentActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spr_selectyourpettype)
    Spinner spr_selectyourpettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sprpettype)
    Spinner sprpettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sprpetbreed)
    Spinner sprpetbreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_continue)
    Button btn_continue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pettype)
    TextView txt_pettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_petbreed)
    TextView txt_petbreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petname)
    EditText edt_petname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_petbreed)
    RelativeLayout rl_petbreed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_pettype)
    RelativeLayout rl_pettype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_or)
    TextView txt_or;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_pet_pics)
    RelativeLayout rl_pet_pics;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_upload_pet_images)
    RecyclerView rv_upload_pet_images;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_pet_imge)
    ImageView img_pet_imge;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_lbl_uploadpet)
    TextView txt_lbl_uploadpet;



    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_comment)
    EditText edt_comment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_selected_service)
    TextView txt_selected_service;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petcolor)
    EditText edt_petcolor;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petweight)
    EditText edt_petweight;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_petage)
    EditText edt_petage;

    private List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList;
    private String strPetType;
    private String strPetBreedType;
    private String userid;
    private String strSelectyourPetType;

    HashMap<String, String> hashMap_PetTypeid = new HashMap<>();
    HashMap<String, String> hashMap_selectyourpet = new HashMap<>();
    private String petTypeId;
    private List<PetDetailsResponse.DataBean> petDetailsResponseByUserIdList;
    private List<BreedTypeResponse.DataBean> breedTypedataBeanList;
    private String petName;
    private String petType;
    private String petBreed;

    private final List<DocBusInfoUploadRequest.ClinicPicBean> clinicPicBeans = new ArrayList<>();

    private static final int REQUEST_CLINIC_CAMERA_PERMISSION_CODE = 785;
    private static final int SELECT_CLINIC_CAMERA = 1000;
    private static final int REQUEST_READ_CLINIC_PIC_PERMISSION = 786;
    private static final int SELECT_CLINIC_PICTURE = 1001;

    MultipartBody.Part filePart;
    String currentDateandTime;
    private String uploadimagepath = "";
    Dialog alertDialog;
    private boolean isSelectYourPet;
    private String selectedAppointmentType = "Emergency";
    private String petId;
    private String doctorid;
    private String petimage;
    private String fromactivity;
    private String fromto;
    private String Payment_id;

    private String SP_ava_Date = "";
    private String selectedTimeSlot = "";

    private String communicationtype;

    private String spid,catid,from;
    private String spuserid;
    private String selectedServiceTitle;
    private String petcolor;
    private int petweight,petage;

    private String servicetime;
    private int serviceamount;





    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_book_appointment);

        ButterKnife.bind(this);

        txt_pettype.setVisibility(View.GONE);
        txt_petbreed.setVisibility(View.GONE);
        img_pet_imge.setVisibility(View.GONE);
        rv_upload_pet_images.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            spuserid = extras.getString("spuserid");
            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            SP_ava_Date = extras.getString("SP_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from+" serviceamount : "+serviceamount+" servicetime : "+servicetime+" SP_ava_Date : "+SP_ava_Date+" selectedTimeSlot : "+selectedTimeSlot);

            if(selectedServiceTitle != null){
                txt_selected_service.setText(selectedServiceTitle);
            }
        }


        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());

        if (userid != null) {
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                petDetailsResponseByUserIdCall();
            }

        }

        img_back.setOnClickListener(v -> onBackPressed());

        spr_selectyourpettype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                strSelectyourPetType = spr_selectyourpettype.getSelectedItem().toString();
                String selectedpetid = hashMap_selectyourpet.get(strSelectyourPetType);

                Log.w(TAG, "strPetType :" + strSelectyourPetType+" selectedpetid : "+selectedpetid);
                if (!strSelectyourPetType.equalsIgnoreCase("Select Your Pet")) {
                    isSelectYourPet = true;
                    txt_or.setVisibility(View.GONE);
                    txt_pettype.setVisibility(View.VISIBLE);
                    txt_petbreed.setVisibility(View.VISIBLE);
                    img_pet_imge.setVisibility(View.VISIBLE);
                    edt_petname.setVisibility(View.GONE);
                    edt_petname.setEnabled(false);
                    edt_petname.setInputType(InputType.TYPE_NULL);

                    if(petDetailsResponseByUserIdList != null && petDetailsResponseByUserIdList.size()>0) {
                        for(int i = 0;i<petDetailsResponseByUserIdList.size();i++) {
                            if(selectedpetid != null && selectedpetid.equalsIgnoreCase(petDetailsResponseByUserIdList.get(i).get_id())) {
                                petName = petDetailsResponseByUserIdList.get(i).getPet_name();
                                petType = petDetailsResponseByUserIdList.get(i).getPet_type();
                                petBreed = petDetailsResponseByUserIdList.get(i).getPet_breed();
                                petId = petDetailsResponseByUserIdList.get(i).get_id();
                                petimage = petDetailsResponseByUserIdList.get(i).getPet_img();
                                petcolor = petDetailsResponseByUserIdList.get(i).getPet_color();
                                petweight = petDetailsResponseByUserIdList.get(i).getPet_weight();
                                petage = petDetailsResponseByUserIdList.get(i).getPet_age();
                                Log.w(TAG, "for petType-->" + petType + "  petcolor : "+petcolor+" petweight : "+petweight+" petage : "+petage);

                            }
                        }
                    }


                    edt_petname.setText(petName);
                    txt_pettype.setText(petType);
                    txt_petbreed.setText(petBreed);
                    Log.w(TAG, "onItemSelected petType-->" + petType + "  petcolor : "+petcolor+" petweight : "+petweight+" petage : "+petage);


                    if(petcolor != null){
                        edt_petcolor.setText(petcolor);
                    }if(petweight != 0){
                        edt_petweight.setText(petweight+"");
                    }if(petage != 0){
                        edt_petage.setText(petage+"");
                    }

                    if(petimage != null){
                        Glide.with(ServiceBookAppointmentActivity.this)
                                .load(petimage)
                                .into(img_pet_imge);
                    }else{
                        Glide.with(ServiceBookAppointmentActivity.this)
                                .load(R.drawable.image_thumbnail)
                                .into(img_pet_imge);

                    }

                    rl_pettype.setVisibility(View.GONE);
                    rl_petbreed.setVisibility(View.GONE);
                    rv_upload_pet_images.setVisibility(View.GONE);
                    txt_lbl_uploadpet.setVisibility(View.GONE);
                    rl_pet_pics.setVisibility(View.GONE);

                }
                else {
                    isSelectYourPet = false;
                    txt_or.setVisibility(View.VISIBLE);
                    edt_petname.setVisibility(View.VISIBLE);


                    txt_pettype.setVisibility(View.GONE);
                    txt_petbreed.setVisibility(View.GONE);
                    img_pet_imge.setVisibility(View.GONE);

                    edt_petname.setText("");
                    edt_petname.setEnabled(true);
                    edt_petname.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

                        edt_petcolor.setText("");

                        edt_petweight.setText("");

                        edt_petage.setText("");


                    rl_pettype.setVisibility(View.VISIBLE);
                    rl_petbreed.setVisibility(View.VISIBLE);
                    rv_upload_pet_images.setVisibility(View.VISIBLE);
                    txt_lbl_uploadpet.setVisibility(View.VISIBLE);
                    rl_pet_pics.setVisibility(View.VISIBLE);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sprpettype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                strPetType = sprpettype.getSelectedItem().toString();
                petTypeId = hashMap_PetTypeid.get(strPetType);
                breedTypeResponseByPetIdCall(petTypeId);

                Log.w(TAG, "petTypeId : " + petTypeId + " strPetType :" + strPetType);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sprpetbreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                strPetBreedType = sprpetbreed.getSelectedItem().toString();
                Log.w(TAG, "strPetBreedType :" + strPetBreedType);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        btn_continue.setOnClickListener(v -> {
            Log.w(TAG,"btn_continue strPetBreedType : "+strPetBreedType);
            if (isSelectYourPet) {
                if(validdSelectYourPetType()){
                   /* if (edt_comment.getText().toString().trim().equals("")) {
                        edt_comment.setError("Please enter comment");
                        edt_comment.requestFocus();
                    }*/
                    startPayment();
                }

            } else {
                if( bookAppointmentValidator()){
                    if (validdSelectPetType()) {
                        if(validdSelectPetBreedType()){

                          /*if (edt_comment.getText().toString().trim().equals("")) {
                                edt_comment.setError("Please enter comment");
                                edt_comment.requestFocus();
                            }else {

                            }*/
                            if (new ConnectionDetector(ServiceBookAppointmentActivity.this).isNetworkAvailable(ServiceBookAppointmentActivity.this)) {
                                addYourPetResponseCall();
                            }
                        }

                    }



                }

            }

        });

        rl_pet_pics.setOnClickListener(v -> choosePetImage());



    }

    @SuppressLint("LongLogTag")
    public void petTypeListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetTypeListResponse> call = apiInterface.petTypeListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<PetTypeListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PetTypeListResponse> call, @NonNull Response<PetTypeListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Log.w(TAG, "PetTypeListResponse" + new Gson().toJson(response.body()));

                        usertypedataBeanList = response.body().getData().getUsertypedata();
                        if (usertypedataBeanList != null && usertypedataBeanList.size() > 0) {
                            setPetType(usertypedataBeanList);
                        }
                    }


                }


            }


            @Override
            public void onFailure(@NonNull Call<PetTypeListResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "PetTypeListResponse flr" + t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private void setPetType(List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList) {
        ArrayList<String> pettypeArrayList = new ArrayList<>();
        pettypeArrayList.add("Select Pet Type");
        for (int i = 0; i < usertypedataBeanList.size(); i++) {

            String petType = usertypedataBeanList.get(i).getPet_type_title();
            hashMap_PetTypeid.put(usertypedataBeanList.get(i).getPet_type_title(), usertypedataBeanList.get(i).get_id());

            Log.w(TAG, "petType-->" + petType);
            pettypeArrayList.add(petType);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(ServiceBookAppointmentActivity.this, R.layout.spinner_item, pettypeArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            sprpettype.setAdapter(spinnerArrayAdapter);


        }
    }


    @SuppressLint("LongLogTag")
    private void breedTypeResponseByPetIdCall(String petTypeId) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<BreedTypeResponse> call = ApiService.breedTypeResponseByPetIdCall(RestUtils.getContentType(), breedTypeRequest(petTypeId));
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<BreedTypeResponse>() {
            @Override
            public void onResponse(@NonNull Call<BreedTypeResponse> call, @NonNull Response<BreedTypeResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "BreedTypeResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        breedTypedataBeanList = response.body().getData();
                        if (breedTypedataBeanList != null && breedTypedataBeanList.size() > 0) {
                            setBreedType(breedTypedataBeanList);
                        }

                    }

                }


            }


            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<BreedTypeResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "BreedTypeResponse flr" + "--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private void setBreedType(List<BreedTypeResponse.DataBean> breedTypedataBeanList) {
        ArrayList<String> pettypeArrayList = new ArrayList<>();
        pettypeArrayList.add("Select Pet Breed");
        for (int i = 0; i < breedTypedataBeanList.size(); i++) {

            String petType = breedTypedataBeanList.get(i).getPet_breed();

            Log.w(TAG, "petType-->" + petType);
            pettypeArrayList.add(petType);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(ServiceBookAppointmentActivity.this, R.layout.spinner_item, pettypeArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            sprpetbreed.setAdapter(spinnerArrayAdapter);


        }
    }

    @SuppressLint("LongLogTag")
    private BreedTypeRequest breedTypeRequest(String petTypeId) {
        BreedTypeRequest breedTypeRequest = new BreedTypeRequest();
        breedTypeRequest.setPet_type_id(petTypeId);
        Log.w(TAG, "breedTypeRequest" + "--->" + new Gson().toJson(breedTypeRequest));
        return breedTypeRequest;
    }

    @SuppressLint("LongLogTag")
    private void petDetailsResponseByUserIdCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetDetailsResponse> call = ApiService.petDetailsResponseByUserIdCall(RestUtils.getContentType(), petDetailsRequest());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<PetDetailsResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<PetDetailsResponse> call, @NonNull Response<PetDetailsResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "PetDetailsResponse" + "--->" + new Gson().toJson(response.body()));

                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

                    petTypeListResponseCall();
                }

                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        petDetailsResponseByUserIdList = response.body().getData();
                        if (petDetailsResponseByUserIdList != null && petDetailsResponseByUserIdList.size() > 0) {
                            setSelectYourPetType(petDetailsResponseByUserIdList);
                        }

                    }

                }

            }


            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<PetDetailsResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "PetDetailsResponse flr" + "--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private PetDetailsRequest petDetailsRequest() {
        PetDetailsRequest petDetailsRequest = new PetDetailsRequest();
        petDetailsRequest.setUser_id(userid);
        Log.w(TAG, "petDetailsRequest" + "--->" + new Gson().toJson(petDetailsRequest));
        return petDetailsRequest;
    }

    @SuppressLint({"LongLogTag", "SetTextI18n"})
    private void setSelectYourPetType(List<PetDetailsResponse.DataBean> petDetailsResponseByUserIdList) {
        ArrayList<String> pettypeArrayList = new ArrayList<>();
        pettypeArrayList.add("Select Your Pet");
        for (int i = 0; i < petDetailsResponseByUserIdList.size(); i++) {
            hashMap_selectyourpet.put(petDetailsResponseByUserIdList.get(i).getPet_name(), petDetailsResponseByUserIdList.get(i).get_id());
            String petName = petDetailsResponseByUserIdList.get(i).getPet_name();
            Log.w(TAG, "petName-->" + petName);
            pettypeArrayList.add(petName);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(ServiceBookAppointmentActivity.this, R.layout.spinner_item, pettypeArrayList);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view
            spr_selectyourpettype.setAdapter(spinnerArrayAdapter);


        }
    }

    public boolean validdSelectYourPetType() {
        if (strSelectyourPetType.equalsIgnoreCase("Select Your Pet")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(ServiceBookAppointmentActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_pettype));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }

    public boolean validdSelectPetType() {
        if (strPetType != null && strPetType.equalsIgnoreCase("Select Pet Type")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(ServiceBookAppointmentActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_pettype));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }

    public boolean validdSelectPetBreedType() {
        if (strPetBreedType != null && strPetBreedType.equalsIgnoreCase("Select Pet Breed")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(ServiceBookAppointmentActivity.this).create();
            alertDialog.setMessage(getString(R.string.err_msg_type_of_petbreedtype));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    (dialog, which) -> alertDialog.cancel());
            alertDialog.show();

            return false;
        }

        return true;
    }


    private void choosePetImage() {

        if (clinicPicBeans.size() >= 1) {

            Toasty.warning(getApplicationContext(), "Sorry you can't Add more than 1", Toast.LENGTH_SHORT).show();

        } else {
            final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
            //AlertDialog.Builder alert=new AlertDialog.Builder(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(ServiceBookAppointmentActivity.this);
            builder.setTitle("Choose option");
            builder.setItems(items, (dialog, item) -> {
                if (items[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ServiceBookAppointmentActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CLINIC_CAMERA_PERMISSION_CODE);
                    } else {


                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(intent, SELECT_CLINIC_CAMERA);
                    }

                } else if (items[item].equals("Choose from Library")) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(ServiceBookAppointmentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_CLINIC_PIC_PERMISSION);
                    } else {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_CLINIC_PICTURE);


                    }
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //	Toast.makeText(getActivity(),"kk",Toast.LENGTH_SHORT).show();
        if (requestCode == SELECT_CLINIC_PICTURE || requestCode == SELECT_CLINIC_CAMERA) {

            if (requestCode == SELECT_CLINIC_CAMERA) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                File file = new File(getFilesDir(), "Petfolio1" + ".jpg");

                OutputStream os;
                try {
                    os = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.flush();
                    os.close();
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                }

                RequestBody requestFile = RequestBody.create(MediaType.parse("image*/"), file);

                filePart = MultipartBody.Part.createFormData("sampleFile", userid+file.getName().trim(), requestFile);

                uploadPetImage();

            } else {

                try {
                    if (resultCode == Activity.RESULT_OK) {

                        Log.w("VALUEEEEEEE1111", " " + data);

                        Uri selectedImageUri = data.getData();

                        Log.w("selectedImageUri", " " + selectedImageUri);

                        String filename = getFileName(selectedImageUri);

                        Log.w("filename", " " + filename);

                        String filePath = FileUtil.getPath(ServiceBookAppointmentActivity.this, selectedImageUri);

                        assert filePath != null;

                        File file = new File(filePath); // initialize file here

                        long length = file.length() / 1024; // Size in KB

                        Log.w("filesize", " " + length);
                        filePart = MultipartBody.Part.createFormData("sampleFile", userid+currentDateandTime+file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                        uploadPetImage();


                    }
                } catch (Exception e) {

                    Log.w("Exception", " " + e);
                }

            }

        }


    }

    @SuppressLint("LongLogTag")
    private void uploadPetImage() {

        avi_indicator.show();

        RestApiInterface apiInterface = APIClient.getImageClient().create(RestApiInterface.class);


        Call<FileUploadResponse> call = apiInterface.getImageStroeResponse(filePart);


        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FileUploadResponse>() {
            @Override
            public void onResponse(@NonNull Call<FileUploadResponse> call, @NonNull Response<FileUploadResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "Profpic" + "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        // FileUploadResponse fileUploadResponse = new FileUploadResponse(response.body().getStatus(),response.body().getMessage(),response.body().getData(),response.body().getCode());
                        DocBusInfoUploadRequest.ClinicPicBean clinicPicBean = new DocBusInfoUploadRequest.ClinicPicBean(response.body().getData().trim());
                        clinicPicBeans.add(clinicPicBean);
                        Log.w(TAG, "clinicPicBeans : " + new Gson().toJson(clinicPicBeans));
                        Log.w(TAG, "uploadimagepath " + response.body().getData());
                        Log.w(TAG, "clinicPicBeans size " + clinicPicBeans.size());
                        uploadimagepath = response.body().getData();
                        if (uploadimagepath != null) {
                            setView();
                        }


                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<FileUploadResponse> call, @NonNull Throwable t) {
                // avi_indicator.smoothToHide();
                Log.w(TAG, "ServerUrlImagePath" + "On failure working" + t.getMessage());
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setView() {
        rv_upload_pet_images.setVisibility(View.VISIBLE);
        rv_upload_pet_images.setLayoutManager(new LinearLayoutManager(this));
        rv_upload_pet_images.setItemAnimator(new DefaultItemAnimator());
        AddImageListAdapter addImageListAdapter = new AddImageListAdapter(getApplicationContext(), clinicPicBeans);
        rv_upload_pet_images.setAdapter(addImageListAdapter);
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }


    @SuppressLint("LongLogTag")
    private void addYourPetResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddYourPetResponse> call = apiInterface.addYourPetResponseCall(RestUtils.getContentType(), addYourPetRequest());
        Log.w(TAG, "AddYourPetResponse url  :%s" + " " + call.request().url().toString());

        call.enqueue(new Callback<AddYourPetResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddYourPetResponse> call, @NonNull Response<AddYourPetResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "AddYourPetResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {
                    if (200 == response.body().getCode()) {
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        petId = response.body().getData().get_id();

                        startPayment();
                        /*Intent intent = new Intent(BookAppointmentActivity.this, PetAppointment_Doctor_Date_Time_Activity.class);
                        intent.putExtra("petid",response.body().getData().get_id());
                        intent.putExtra("doctorid",doctorid);
                        intent.putExtra("allergies",edt_allergies.getText().toString());
                        intent.putExtra("probleminfo",edt_comment.getText().toString());
                        intent.putExtra("selectedAppointmentType",selectedAppointmentType);
                        Log.w(TAG,"selectedAppointmentType : "+selectedAppointmentType);
                        startActivity(intent);
*/
                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AddYourPetResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("AddYourPetResponse flr", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddYourPetRequest addYourPetRequest() {
        /*
         * user_id : 5fb36ca169f71e30a0ffd3f7
         * pet_img : http://mysalveo.com/api/uploads/images.jpeg
         * pet_name : POP
         * pet_type : Dog
         * pet_breed : breed 1
         * pet_gender : Male
         * pet_color : white
         * pet_weight : 120
         * pet_age : 20
         * vaccinated : true
         * last_vaccination_date : 23-10-1996
         * default_status : true
         * date_and_time : 23-10-1996 12:09 AM
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        AddYourPetRequest addYourPetRequest = new AddYourPetRequest();
        addYourPetRequest.setUser_id(userid);
        addYourPetRequest.setPet_img(uploadimagepath);
        addYourPetRequest.setPet_name(edt_petname.getText().toString());
        addYourPetRequest.setPet_type(strPetType);
        addYourPetRequest.setPet_breed(strPetBreedType);
        addYourPetRequest.setPet_gender("");
        addYourPetRequest.setPet_color(edt_petcolor.getText().toString());
        addYourPetRequest.setPet_weight(Integer.parseInt(edt_petweight.getText().toString()));
        addYourPetRequest.setPet_age(Integer.parseInt(edt_petage.getText().toString()));
        addYourPetRequest.setVaccinated(false);
        addYourPetRequest.setLast_vaccination_date("");
        addYourPetRequest.setDefault_status(true);
        addYourPetRequest.setDate_and_time(currentDateandTime);
        addYourPetRequest.setMobile_type("Android");
        Log.w(TAG, "addYourPetRequest" + new Gson().toJson(addYourPetRequest));
        return addYourPetRequest;
    }

    public void showErrorLoading(String errormesage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading() {
        try {
            alertDialog.dismiss();
        } catch (Exception ignored) {

        }
    }


    public boolean bookAppointmentValidator() {
        boolean can_proceed = true;

        int petweightlength = edt_petweight.getText().toString().trim().length();


        if (edt_petname.getText().toString().trim().equals("")) {
            edt_petname.setError("Please enter pet name");
            edt_petname.requestFocus();
            can_proceed = false;
        }else if (edt_petcolor.getText().toString().trim().equals("")) {
            edt_petcolor.setError("Please enter pet color");
            edt_petcolor.requestFocus();
            can_proceed = false;
        }else if (edt_petweight.getText().toString().trim().equals("")) {
            edt_petweight.setError("Please enter pet weight");
            edt_petweight.requestFocus();
            can_proceed = false;
        }
        else if (petweightlength > 5) {
            edt_petweight.setError("The maximum length for an pet weight is 5 characters.");
            edt_petweight.requestFocus();
            can_proceed = false;
        }
        else if (edt_petage.getText().toString().trim().equals("")) {
            edt_petage.setError("Please enter pet age");
            edt_petage.requestFocus();
            can_proceed = false;
        }


        return can_proceed;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(),PetServiceAppointment_Doctor_Date_Time_Activity.class);
        intent.putExtra("spid",spid);
        intent.putExtra("catid",catid);
        intent.putExtra("from",from);
        intent.putExtra("spuserid",spuserid);
        intent.putExtra("selectedServiceTitle",selectedServiceTitle);
        intent.putExtra("serviceamount",serviceamount);
        intent.putExtra("servicetime",servicetime);
        startActivity(intent);




    }


    @SuppressLint("LongLogTag")
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        //totalamount = amount;

      /*  Double d = new Double(amount);
        int amout = d.intValue();*/


        Integer totalamout = serviceamount*100;

        try {
            JSONObject options = new JSONObject();
            options.put("name", "PetFolio");
            options.put("description", userid);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", totalamout);


            co.open(activity, options);
        } catch (Exception e) {
            Log.w(TAG,"Error in payment: " + e.getMessage());

            e.printStackTrace();
        }
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Payment_id = razorpayPaymentID;

            Log.w(TAG, "Payment Successful: " + razorpayPaymentID);
            Toasty.success(getApplicationContext(), "Payment Successful. View your booking details in upcoming appointments.", Toast.LENGTH_SHORT, true).show();


            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                spCreateAppointmentResponseCall();

            }




        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentSuccess", e);
        }
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Log.w(TAG,  "Payment failed: " + code + " " + response);
            Toasty.error(getApplicationContext(), "Payment failed. Please try again with another payment method..", Toast.LENGTH_SHORT, true).show();

        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentError", e);
        }
    }


    @SuppressLint("LongLogTag")
    private void spCreateAppointmentResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SPCreateAppointmentResponse> call = ApiService.SPCreateAppointmentResponseCall(RestUtils.getContentType(),spCreateAppointmentRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SPCreateAppointmentResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<SPCreateAppointmentResponse> call, @NonNull Response<SPCreateAppointmentResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPCreateAppointmentResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        if(response.body().getMessage() != null){
                            showSuceessLoading(response.body().getMessage());

                        }



                    }
                    else{
                        if(response.body().getMessage() != null){
                            showErrorLoading(response.body().getMessage());

                        }


                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<SPCreateAppointmentResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"SPCreateAppointmentResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LongLogTag")
    private SPCreateAppointmentRequest spCreateAppointmentRequest() {
        /*
         * sp_id : 5ff7ef9b1c72093650a13a10
         * booking_date : 23/10/2020
         * booking_time : 12:00 AM
         * booking_date_time : 23/10/2020 12:00 AM
         * user_id : 5fd841a67aa4cc1c6a1e5636
         * pet_id : 5fdc46be1e5d8b0eb31c3699
         * additional_info : this if is for the comments
         * sp_attched : []
         * sp_feedback :
         * sp_rate :
         * user_feedback :
         * user_rate :
         * display_date : 23/10/2020 10:10 AM
         * server_date_time : 23/10/2020 10:10 AM
         * payment_id : 12345
         * payment_method : Card
         * service_name : Grooming
         * service_amount : 200
         * service_time : 15 mins
         * completed_at :
         * missed_at :
         * mobile_type : Admin
         */
        List<SPCreateAppointmentRequest.SpAttchedBean> sp_attched = new ArrayList<>();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime = simpleDateFormat.format(new Date());
        /*String currenttime = currentDateandTime.substring(currentDateandTime.indexOf(' ') + 1);
        String currentdate =  currentDateandTime.substring(0, currentDateandTime.indexOf(' '));*/

        SPCreateAppointmentRequest spCreateAppointmentRequest = new SPCreateAppointmentRequest();
        spCreateAppointmentRequest.setSp_id(spuserid);
        spCreateAppointmentRequest.setBooking_date(SP_ava_Date);
        spCreateAppointmentRequest.setBooking_time(selectedTimeSlot);
        spCreateAppointmentRequest.setBooking_date_time(SP_ava_Date+" "+selectedTimeSlot);
        spCreateAppointmentRequest.setUser_id(userid);
        spCreateAppointmentRequest.setPet_id(petId);
        spCreateAppointmentRequest.setAdditional_info(edt_comment.getText().toString());
        spCreateAppointmentRequest.setSp_attched(sp_attched);
        spCreateAppointmentRequest.setSp_feedback("");
        spCreateAppointmentRequest.setSp_rate("");
        spCreateAppointmentRequest.setUser_feedback("");
        spCreateAppointmentRequest.setUser_rate("");
        spCreateAppointmentRequest.setDisplay_date(currentDateandTime);
        spCreateAppointmentRequest.setServer_date_time("");
        spCreateAppointmentRequest.setPayment_id(Payment_id);
        spCreateAppointmentRequest.setPayment_method("Online");
        spCreateAppointmentRequest.setService_name(selectedServiceTitle);
        spCreateAppointmentRequest.setService_amount(String.valueOf(serviceamount));
        spCreateAppointmentRequest.setService_time(servicetime);
        spCreateAppointmentRequest.setCompleted_at("");
        spCreateAppointmentRequest.setMissed_at("");
        spCreateAppointmentRequest.setMobile_type("Android");
        Log.w(TAG,"spCreateAppointmentRequest"+ "--->" + new Gson().toJson(spCreateAppointmentRequest));
        return spCreateAppointmentRequest;
    }
    public void showSuceessLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoadingSuccess());



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void hideLoadingSuccess() {
        try {
            Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
            startActivity(intent);
            alertDialog.dismiss();

        } catch (Exception ignored) {

        }
    }


}







