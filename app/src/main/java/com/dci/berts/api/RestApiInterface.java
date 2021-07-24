package com.dci.berts.api;


import com.dci.berts.model.DeleteCartListRequest;
import com.dci.berts.requestpojo.AddToCartRequest;
import com.dci.berts.requestpojo.AddVehicleRequest;
import com.dci.berts.requestpojo.AddWishistRequest;
import com.dci.berts.requestpojo.CreateAddressListRequest;
import com.dci.berts.requestpojo.DeleteAddressListRequest;
import com.dci.berts.requestpojo.DeleteVehicleRequest;
import com.dci.berts.requestpojo.EditAddressListRequest;
import com.dci.berts.requestpojo.EditVehicleRequest;
import com.dci.berts.requestpojo.FacebookLoginRequest;
import com.dci.berts.requestpojo.FetchAllColorsRequest;
import com.dci.berts.requestpojo.FetchAllYearRequest;
import com.dci.berts.requestpojo.FetchChildCateglistRequest;
import com.dci.berts.requestpojo.FetchChildMakeslistRequest;
import com.dci.berts.requestpojo.FetchProductBasedOnBrandRequest;
import com.dci.berts.requestpojo.FetchProductBasedOnCatRequest;
import com.dci.berts.requestpojo.FetchProductBasedOnMakeRequest;
import com.dci.berts.requestpojo.FilterRequest;
import com.dci.berts.requestpojo.ForgotPasswordRequest;
import com.dci.berts.requestpojo.GetStateRequest;
import com.dci.berts.requestpojo.HomepageDashboardRequest;
import com.dci.berts.requestpojo.HomepageDashboardResponse;
import com.dci.berts.requestpojo.LoginRequest;
import com.dci.berts.requestpojo.OrderCancelOverallRequest;
import com.dci.berts.requestpojo.OrderCreateRequest;
import com.dci.berts.requestpojo.OrderDetailListRequest;
import com.dci.berts.requestpojo.ProductDetailRequest;
import com.dci.berts.requestpojo.RemoveOverallProductsRequest;
import com.dci.berts.requestpojo.RemoveWishistRequest;
import com.dci.berts.requestpojo.RemovefromCartRequest;
import com.dci.berts.requestpojo.SearchProductsRequest;
import com.dci.berts.requestpojo.SetDefaultAddrRequest;
import com.dci.berts.requestpojo.SetDefaultVehicleRequest;
import com.dci.berts.requestpojo.ShowCartListRequest;
import com.dci.berts.requestpojo.ShowOrderlistRequest;
import com.dci.berts.requestpojo.ShowVehiclelistRequest;
import com.dci.berts.requestpojo.ShowWishistRequest;
import com.dci.berts.requestpojo.SignupRequest;
import com.dci.berts.requestpojo.SignupWholesalerRequest;
import com.dci.berts.requestpojo.UpdateProfileRequest;
import com.dci.berts.requestpojo.UserAddressListRequest;
import com.dci.berts.responsepojo.AddToCartResponse;
import com.dci.berts.responsepojo.AddVehicleResponse;
import com.dci.berts.responsepojo.CreateAddressListResponse;
import com.dci.berts.responsepojo.DeleteAddressListResponse;
import com.dci.berts.responsepojo.DeleteVehicleResponse;
import com.dci.berts.responsepojo.EditAddressListResponse;
import com.dci.berts.responsepojo.EditVehicleResponse;
import com.dci.berts.responsepojo.FetchAllBrandsResponse;
import com.dci.berts.responsepojo.FetchAllColorsResponse;
import com.dci.berts.responsepojo.FetchAllParentCategoriesResponse;
import com.dci.berts.responsepojo.FetchAllParentMakesResponse;
import com.dci.berts.responsepojo.FetchAllYearResponse;
import com.dci.berts.responsepojo.FetchChildCateglistResponse;
import com.dci.berts.responsepojo.FetchChildMakeslistRequestResponse;
import com.dci.berts.responsepojo.FilterResponse;
import com.dci.berts.responsepojo.ForgotPasswordResponse;
import com.dci.berts.responsepojo.GetCountryResponse;
import com.dci.berts.responsepojo.GetStateResponse;
import com.dci.berts.responsepojo.LoginResponse;
import com.dci.berts.responsepojo.OrderCancelOverallResponse;
import com.dci.berts.responsepojo.OrderCreateResponse;
import com.dci.berts.responsepojo.OrderDetailListResponse;
import com.dci.berts.responsepojo.ProductDetailRespone;
import com.dci.berts.responsepojo.ProductListResponse;
import com.dci.berts.responsepojo.RemoveOverallProductsResponse;
import com.dci.berts.responsepojo.RemovefromCartResponse;
import com.dci.berts.responsepojo.SearchProductsResponse;
import com.dci.berts.responsepojo.SetDefaultAddrResponse;
import com.dci.berts.responsepojo.SetDefaultVehicleResponse;
import com.dci.berts.responsepojo.ShowCartListResponse;
import com.dci.berts.responsepojo.ShowOrderlistResponse;
import com.dci.berts.responsepojo.ShowVehiclelistResponse;
import com.dci.berts.responsepojo.SignupResponse;
import com.dci.berts.responsepojo.UpdateProfileResponse;
import com.dci.berts.responsepojo.UserAddressListResponse;
import com.dci.berts.responsepojo.WishlistSuccessResponse;

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
