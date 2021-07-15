package com.triton.bertsproject.api;


import com.triton.bertsproject.model.DeleteCartListRequest;
import com.triton.bertsproject.requestpojo.AddToCartRequest;
import com.triton.bertsproject.requestpojo.AddVehicleRequest;
import com.triton.bertsproject.requestpojo.AddWishistRequest;
import com.triton.bertsproject.requestpojo.CreateAddressListRequest;
import com.triton.bertsproject.requestpojo.DeleteAddressListRequest;
import com.triton.bertsproject.requestpojo.DeleteVehicleRequest;
import com.triton.bertsproject.requestpojo.EditAddressListRequest;
import com.triton.bertsproject.requestpojo.EditVehicleRequest;
import com.triton.bertsproject.requestpojo.FacebookLoginRequest;
import com.triton.bertsproject.requestpojo.FetchAllColorsRequest;
import com.triton.bertsproject.requestpojo.FetchAllYearRequest;
import com.triton.bertsproject.requestpojo.FetchChildCateglistRequest;
import com.triton.bertsproject.requestpojo.FetchChildMakeslistRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnBrandRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnCatRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnMakeRequest;
import com.triton.bertsproject.requestpojo.FilterRequest;
import com.triton.bertsproject.requestpojo.ForgotPasswordRequest;
import com.triton.bertsproject.requestpojo.GetStateRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardResponse;
import com.triton.bertsproject.requestpojo.LoginRequest;
import com.triton.bertsproject.requestpojo.OrderCancelOverallRequest;
import com.triton.bertsproject.requestpojo.OrderCreateRequest;
import com.triton.bertsproject.requestpojo.OrderDetailListRequest;
import com.triton.bertsproject.requestpojo.ProductDetailRequest;
import com.triton.bertsproject.requestpojo.RemoveOverallProductsRequest;
import com.triton.bertsproject.requestpojo.RemoveWishistRequest;
import com.triton.bertsproject.requestpojo.RemovefromCartRequest;
import com.triton.bertsproject.requestpojo.SearchProductsRequest;
import com.triton.bertsproject.requestpojo.SetDefaultAddrRequest;
import com.triton.bertsproject.requestpojo.SetDefaultVehicleRequest;
import com.triton.bertsproject.requestpojo.ShowCartListRequest;
import com.triton.bertsproject.requestpojo.ShowOrderlistRequest;
import com.triton.bertsproject.requestpojo.ShowVehiclelistRequest;
import com.triton.bertsproject.requestpojo.ShowWishistRequest;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.requestpojo.SignupWholesalerRequest;
import com.triton.bertsproject.requestpojo.UpdateProfileRequest;
import com.triton.bertsproject.requestpojo.UserAddressListRequest;
import com.triton.bertsproject.responsepojo.AddToCartResponse;
import com.triton.bertsproject.responsepojo.AddVehicleResponse;
import com.triton.bertsproject.responsepojo.CreateAddressListResponse;
import com.triton.bertsproject.responsepojo.DeleteAddressListResponse;
import com.triton.bertsproject.responsepojo.DeleteVehicleResponse;
import com.triton.bertsproject.responsepojo.EditAddressListResponse;
import com.triton.bertsproject.responsepojo.EditVehicleResponse;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.FetchAllColorsResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchAllYearResponse;
import com.triton.bertsproject.responsepojo.FetchChildCateglistResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;
import com.triton.bertsproject.responsepojo.FilterResponse;
import com.triton.bertsproject.responsepojo.ForgotPasswordResponse;
import com.triton.bertsproject.responsepojo.GetCountryResponse;
import com.triton.bertsproject.responsepojo.GetStateResponse;
import com.triton.bertsproject.responsepojo.LoginResponse;
import com.triton.bertsproject.responsepojo.OrderCancelOverallResponse;
import com.triton.bertsproject.responsepojo.OrderCreateResponse;
import com.triton.bertsproject.responsepojo.OrderDetailListResponse;
import com.triton.bertsproject.responsepojo.ProductDetailRespone;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.responsepojo.RemoveOverallProductsResponse;
import com.triton.bertsproject.responsepojo.RemovefromCartResponse;
import com.triton.bertsproject.responsepojo.SearchProductsResponse;
import com.triton.bertsproject.responsepojo.SetDefaultAddrResponse;
import com.triton.bertsproject.responsepojo.SetDefaultVehicleResponse;
import com.triton.bertsproject.responsepojo.ShowCartListResponse;
import com.triton.bertsproject.responsepojo.ShowOrderlistResponse;
import com.triton.bertsproject.responsepojo.ShowVehiclelistResponse;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.responsepojo.UpdateProfileResponse;
import com.triton.bertsproject.responsepojo.UserAddressListResponse;
import com.triton.bertsproject.responsepojo.WishlistSuccessResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestApiInterface {

    /*User Login*/
    @POST("login-customer")
    Call<LoginResponse> loginResponseCall(@Header("Content-Type") String type, @Body LoginRequest loginRequest);

    /*User Login*/
    @POST("login-customer")
    Call<LoginResponse> fbloginResponseCall(@Header("Content-Type") String type, @Body FacebookLoginRequest facebookLoginRequest);

    /*Signup create*/
    @POST("register-customer")
    Call<SignupResponse> signupResponseCall(@Header("Content-Type") String type, @Body SignupRequest signupRequest);

    /*Signup create*/
    @POST("register-customer")
    Call<SignupResponse> signupWholesalerResponseCall(@Header("Content-Type") String type, @Body SignupWholesalerRequest signupWholesalerRequest);

    /*Home Page*/
    @POST("usercommon")
    Call<HomepageDashboardResponse> usercommonResponseCall(@Header("Content-Type") String type, @Body HomepageDashboardRequest homepageDashboardRequest);

    /*forgot-password*/
    @POST("forgot-password")
    Call<ForgotPasswordResponse> forgotpasswordResponseCall(@Header("Content-Type") String type, @Body ForgotPasswordRequest forgotPasswordRequest);

    /*Get All Countries*/
    @GET("country")
    Call<GetCountryResponse> fetchallcountryListResponseCall(@Header("Content-Type") String type);

    /*Get All States*/
    @POST("country")
    Call<GetStateResponse> fetchallstateListResponseCall(@Header("Content-Type") String type, @Body GetStateRequest getStateRequest);

    /*Profile Update*/
    @POST("update-customer")
    Call<UpdateProfileResponse> updateResponseCall(@Header("Content-Type") String type, @Body UpdateProfileRequest updateProfileRequest);

    /*Get All Parent Categories*/
    @GET("category")
    Call<FetchAllParentCategoriesResponse> fetchallcategoriesListResponseCall(@Header("Content-Type") String type);

    /*Get All Brands*/
    @GET("brand")
    Call<FetchAllBrandsResponse> fetchallbrandsListResponseCall(@Header("Content-Type") String type);

    /*Get All Parent Makes*/
    @GET("make")
    Call<FetchAllParentMakesResponse> fetchallmakesListResponseCall(@Header("Content-Type") String type);

    /*Fetch Child Category List*/
    @POST("category")
    Call<FetchChildCateglistResponse> fetchallchildcateglistResponseCall(@Header("Content-Type") String type, @Body FetchChildCateglistRequest fetchChildCateglistRequest);

    /*Fetch Child Makes List*/
    @POST("make")
    Call<FetchChildMakeslistRequestResponse> fetchallchildmakelistResponseCall(@Header("Content-Type") String type, @Body FetchChildMakeslistRequest fetchChildMakeslistRequest);

    /*Fetch Product based on Brand*/
    @POST("product")
    Call<ProductListResponse> fetchallprodbasedonbrandlistResponseCall(@Header("Content-Type") String type, @Body FetchProductBasedOnBrandRequest fetchProductBasedOnBrandRequest);

    /*Fetch Product based on category*/
    @POST("product")
    Call<ProductListResponse> fetchallprodbasedonCatlistResponseCall(@Header("Content-Type") String type, @Body FetchProductBasedOnCatRequest fetchProductBasedOnCatRequest);

    /*Fetch Product based on Make*/
    @POST("product")
    Call<ProductListResponse> fetchallprodbasedonmakelistResponseCall(@Header("Content-Type") String type, @Body FetchProductBasedOnMakeRequest fetchProductBasedOnMakeRequest);

    /*add wishlist*/
    @POST("wishlist")
    Call<WishlistSuccessResponse> wishlistaddResponseCall(@Header("Content-Type") String type, @Body AddWishistRequest addWishistRequest);

    /*show wishlist*/
    @POST("wishlist")
    Call<WishlistSuccessResponse> showwishlistResponseCall(@Header("Content-Type") String type, @Body ShowWishistRequest showWishistRequest);

    /*delete wishlist*/
    @POST("wishlist")
    Call<WishlistSuccessResponse> deletewishlistResponseCall(@Header("Content-Type") String type, @Body RemoveWishistRequest removeWishistRequest);

    /*Add to cart*/
    @POST("cart")
    Call<AddToCartResponse> addcartlistResponseCall(@Header("Content-Type") String type, @Body AddToCartRequest addToCartRequest);

    /*Remove From cart*/
    @POST("cart")
    Call<RemovefromCartResponse> removefromcartlistResponseCall(@Header("Content-Type") String type, @Body RemovefromCartRequest removefromCartRequest);

    /*show cart*/
    @POST("cart")
    Call<ShowCartListResponse> showcartlistResponseCall(@Header("Content-Type") String type, @Body ShowCartListRequest showCartListRequest);

    /*delete cart*/
    @POST("cart")
    Call<ShowCartListResponse> deletecartlistResponseCall(@Header("Content-Type") String type, @Body DeleteCartListRequest deleteCartListRequest);

    /*delete overall product from cart*/
    @POST("cart")
    Call<RemoveOverallProductsResponse> deleteoverallcartlistResponseCall(@Header("Content-Type") String type, @Body RemoveOverallProductsRequest removeOverallProductsRequest);

    /*addvehicle*/
    @POST("addvehicle")
    Call<AddVehicleResponse> addvehiclelistResponseCall(@Header("Content-Type") String type, @Body AddVehicleRequest addVehicleRequest);

    /*showvehicle*/
    @POST("addvehicle")
    Call<ShowVehiclelistResponse> showvehiclelistResponseCall(@Header("Content-Type") String type, @Body ShowVehiclelistRequest showVehiclelistRequest);

    /*showvehicle*/
    @POST("addvehicle")
    Call<SetDefaultVehicleResponse> setdefaultVehResponseCall(@Header("Content-Type") String type, @Body SetDefaultVehicleRequest setDefaultVehicleRequest);

    /*deletevehicle*/
    @POST("addvehicle")
    Call<DeleteVehicleResponse> deletevehiclelistResponseCall(@Header("Content-Type") String type, @Body DeleteVehicleRequest deleteVehicleRequest);

    /*Get All Years*/
    @POST("common")
    Call<FetchAllYearResponse> fetchallyearListResponseCall(@Header("Content-Type") String type, @Body FetchAllYearRequest fetchAllYearRequest);

    /*Get All Colors*/
    @POST("common")
    Call<FetchAllColorsResponse> fetchallcolorListResponseCall(@Header("Content-Type") String type, @Body FetchAllColorsRequest fetchAllColorsRequest);

    /*Edit Vehicle List*/
    @POST("addvehicle")
    Call<EditVehicleResponse> editvehicleListResponseCall(@Header("Content-Type") String type, @Body EditVehicleRequest editVehicleRequest);

    /*Order Create*/
    @POST("order")
    Call<OrderCreateResponse> orderCreateListResponseCall(@Header("Content-Type") String type, @Body OrderCreateRequest orderCreateRequest);

    /*Order List*/
    @POST("order")
    Call<ShowOrderlistResponse> orderListResponseCall(@Header("Content-Type") String type, @Body ShowOrderlistRequest showOrderlistRequest);

    /*Order Detail List*/
    @POST("order")
    Call<OrderDetailListResponse> orderDetailListResponseCall(@Header("Content-Type") String type, @Body OrderDetailListRequest orderDetailListRequest);

    /*Cancel Orders*/
    @POST("order")
    Call<OrderCancelOverallResponse> canceloverallOrderResponseCall(@Header("Content-Type") String type, @Body OrderCancelOverallRequest orderCancelOverallRequest);

    /*user address list*/
    @POST("user/address")
    Call<UserAddressListResponse> useraddressListResponseCall(@Header("Content-Type") String type, @Body UserAddressListRequest userAddressListRequest);

    /*create address list*/
    @POST("user/address")
    Call<CreateAddressListResponse> createaddressResponseCall(@Header("Content-Type") String type, @Body CreateAddressListRequest createAddressListRequest);

    /*edit address list*/
    @POST("user/address")
    Call<EditAddressListResponse> editaddressResponseCall(@Header("Content-Type") String type, @Body EditAddressListRequest editAddressListRequest);

    /*delete address list*/
    @POST("user/address")
    Call<DeleteAddressListResponse> deleteaddressResponseCall(@Header("Content-Type") String type, @Body DeleteAddressListRequest deleteAddressListRequest);

    /*set default address list*/
    @POST("user/address")
    Call<SetDefaultAddrResponse> setdefaultaddrResponseCall(@Header("Content-Type") String type, @Body SetDefaultAddrRequest setDefaultAddrRequest);

    /*search product list*/
    @POST("product/search")
    Call<SearchProductsResponse> searchprodResponseCall(@Header("Content-Type") String type, @Body SearchProductsRequest searchProductsRequest);

    /*Fetch Product based on Make*/
    @POST("product")
    Call<ProductDetailRespone> fetchproductdetailsResponseCall(@Header("Content-Type") String type, @Body ProductDetailRequest productDetailRequest);

    /*Filter Product*/
    @POST("product")
    Call<FilterResponse> fetchallfilterprodResponseCall(@Header("Content-Type") String type, @Body FilterRequest filterRequest);

}
