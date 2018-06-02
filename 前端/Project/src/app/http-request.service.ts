import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()

export class HttpRequestService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json;application/x-www-form-urlencodeed; charset=utf-8'})
  };

  constructor(private httpClient: HttpClient) { }

  httpPost(reqUrl: string, reqBody, comp, flag) {
    this.httpClient.post(reqUrl, reqBody, this.httpOptions)
      .subscribe(
        value => {
          console.log('post请求成功', value);
          if (value['success']) {
            comp.postOk(value, flag);
          } else {
            comp.postErr(value, flag);
          }
        },
        error => {
          console.log('post请求失败', error);
          comp.postErr(error, flag);
        }
      );
  }

  httpGet(reqUrl, comp, flag) {
    this.httpClient.get(reqUrl, this.httpOptions)
      .subscribe(
        value => {
          console.log('get', value);
          if (value['success']) {
            comp.getOk(value, flag);
          } else {
            comp.getErr(value, flag);
          }
        },
        error => {
          console.log('get', error);
          comp.getErr(error, flag);
        }
      );
  }
}
