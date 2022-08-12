import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
import 'getXController.dart';
import 'http.dart';
import 'oauth.dart';

class LogIn extends StatelessWidget {
  const LogIn({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: const Text(
          'Sign In',
          style: TextStyle(color: Colors.white),
        ),
        centerTitle: true,
        elevation: 0.2,
      ),
      body: buildButton(),
    );
  }

  Widget buildButton() {
    Get.put(XController());
    final controller = Get.find<XController>();
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Obx(
            () => Text("AccessToken : ${controller.accessToken.value}\n"
                    "RefreshToken : ${controller.refreshToken.value}\n" +
                "IdToken : ${controller.idToken.value}\n"),
          ),
          ButtonTheme(
            height: 50.0,
            shape: const RoundedRectangleBorder(
              borderRadius: BorderRadius.all(
                Radius.circular(4.0),
              ),
            ),
            child: RaisedButton(
              color: Colors.yellow,
              onPressed: () async {
                OAuthToken? token = await signIn();
                controller.setString(
                    token!.accessToken, token.refreshToken, token.idToken);
                print("token : " + token.idToken.toString());
              },
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: const <Widget>[
                  Icon(
                    Icons.mail,
                    size: 60.0,
                    color: Colors.white,
                  ),
                  Text(
                    'Login with Kakao',
                    style: TextStyle(color: Colors.black87, fontSize: 15.0),
                  ),
                  Opacity(
                    opacity: 0.0,
                    child: Icon(
                      Icons.mail,
                      size: 60.0,
                      color: Colors.white,
                    ),
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(
            height: 10.0,
          ),
          ButtonTheme(
            height: 50.0,
            shape: const RoundedRectangleBorder(
              borderRadius: BorderRadius.all(
                Radius.circular(4.0),
              ),
            ),
            child: RaisedButton(
              color: const Color(0xFF334D92),
              onPressed: () {
                postRequest(controller.accessToken.value,
                    controller.refreshToken.value, controller.idToken.value);
              },
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: const <Widget>[
                  Icon(
                    Icons.mail,
                    size: 60.0,
                    color: Colors.white,
                  ),
                  Text(
                    'POST',
                    style: TextStyle(color: Colors.white, fontSize: 15.0),
                  ),
                  Opacity(
                    opacity: 0.0,
                    child: Icon(
                      Icons.mail,
                      size: 60.0,
                      color: Colors.white,
                    ),
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(
            height: 10.0,
          ),
          ButtonTheme(
            height: 50.0,
            shape: const RoundedRectangleBorder(
              borderRadius: BorderRadius.all(
                Radius.circular(4.0),
              ),
            ),
            child: RaisedButton(
              color: Colors.green,
              onPressed: () {
                print(getRequest());
              },
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: const <Widget>[
                  Icon(
                    Icons.mail,
                    size: 60.0,
                    color: Colors.white,
                  ),
                  Text(
                    'Get',
                    style: TextStyle(color: Colors.white, fontSize: 15.0),
                  ),
                  Opacity(
                    opacity: 0.0,
                    child: Icon(
                      Icons.mail,
                      size: 60.0,
                      color: Colors.white,
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
