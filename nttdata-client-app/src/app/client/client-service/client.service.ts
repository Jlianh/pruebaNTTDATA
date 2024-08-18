import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  getClientByDocumentTypeAndDocument(data: any) {
    console.log(data);
    const url = `http://localhost:8090/api/v1/client/getClientByDocument?documentType=${data.documentType}&document=${Number(data.document)}`
    return this.http.get(url);
  }
}
