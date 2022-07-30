import 'package:get/get.dart';
import 'package:http/http.dart' as http;

Future<http.Response> fetchAlbum() {
  return http.get(Uri.parse('https://jsonplaceholder.typicode.com/albums/1'));
}

Future<http.Response> postRequest(
    String accessToken, String refreshToken, String idToken) async {
  Uri url = Uri.parse('http://10.0.2.2:8080/users');

  http.Response response = await http.post(
    url,
    headers: <String, String>{
      // 'Content-Type': 'application/json',
      'connection': 'keep-alive',
    },
    body: <String, String>{
      'AccessToken': accessToken,
      'RefreshToken': refreshToken,
      'IdToken': idToken
    },
  );
  return response;
}

Future<http.Response> getRequest() async {
  Uri url = Uri.parse('https://localhost:8080/users');

  http.Response response = await http.post(
    url,
    // headers: <String, String>{
    //   'Content-Type': 'application/json',
    // },
    body: <String, String>{
      'AccessToken': '8Uxvm4fNkVpgOlf6CYw1MIq2aYgB7rqWo7B1J9mpCj11WgAAAYJIrGGs',
      'user': 'jykim'
    },
  );
  return response;
}
