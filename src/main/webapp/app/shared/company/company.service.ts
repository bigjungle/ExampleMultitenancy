import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { Company, ICompany } from '../../admin/company-management/company.model';
import { createRequestOption } from '../../shared';

@Injectable({ providedIn: 'root' })
export class CompanyService {
    private resourceUrl = SERVER_API_URL + 'api/companies';

    constructor(private http: HttpClient) {}

    create(company: ICompany): Observable<HttpResponse<ICompany>> {
        return this.http.post<ICompany>(this.resourceUrl, company, { observe: 'response' });
    }

    update(company: ICompany): Observable<HttpResponse<ICompany>> {
        return this.http.put<ICompany>(this.resourceUrl, company, { observe: 'response' });
    }

    find(id: number): Observable<HttpResponse<ICompany>> {
        return this.http.get<ICompany>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<HttpResponse<Company[]>> {
        const options = createRequestOption(req);
        return this.http.get<ICompany[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
