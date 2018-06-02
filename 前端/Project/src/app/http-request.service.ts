import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()

export class HttpRequestService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json;application/x-www-form-urlencodeed; charset=utf-8'})
  };

  constructor(private httpClient: HttpClient) { }

  httpPost(reqUrl: string, reqBody, comp) {
    this.httpClient.post(reqUrl, reqBody, this.httpOptions)
      .subscribe(
        value => {
          console.log('post请求成功', value);
          if (value['success']) {
            comp.postOk(value);
          } else {
            comp.postErr(value);
          }
        },
        error => {
          console.log('post请求失败', error);
          comp.postErr(error);
        }
      );
  }

  httpGet(reqUrl, comp) {
    this.httpClient.get(reqUrl, this.httpOptions)
      .subscribe(
        value => {
          console.log('get请求成功', value);
          if (value['success']) {
            comp.getOk(value);
          } else {
            comp.getErr(value);
          }
        },
        error => {
          console.log('get请求失败', error);
          comp.getErr(error);
        }
      );
  }
}
