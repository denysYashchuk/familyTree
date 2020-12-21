import {Injectable} from '@angular/core';
import {AppSettings} from '../app-settings';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  private fileUrl = AppSettings.BASE_API_ENDPOINT + '/family-members/{ID}/files';

  constructor(private client: HttpClient) {
  }

  upload(file: File, id: number): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', this.fileUrl.replace('{ID}', String(id)), formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.client.request(req);
  }

}
