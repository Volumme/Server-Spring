import 'package:flutter/material.dart';
import 'package:kakao_flutter_sdk_common/kakao_flutter_sdk_common.dart';
import 'login.dart';
import 'package:get/get.dart';

void main() {
  KakaoSdk.init(nativeAppKey: 'e5f9a7629df9bf988dac8d499f8559ea');
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'login app',
      theme: ThemeData(primarySwatch: Colors.grey),
      home: const LogIn(),
    );
  }
}
