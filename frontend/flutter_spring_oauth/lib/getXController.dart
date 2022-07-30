import 'package:get/get.dart';

class XController extends GetxController {
  RxString accessToken = "No name!".obs;
  RxString refreshToken = "No name!".obs;
  RxString idToken = "No name!".obs;

  void setString(String aToken, String rToken, String? iToken) {
    accessToken.value = aToken;
    refreshToken.value = rToken;
    if (iToken != null) idToken.value = iToken;
  }
}
