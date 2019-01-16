import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';

import { Company } from 'app/admin';
import { CompanyService } from 'app/shared';
import { CompanyMgmtComponent } from './company-management.component';
import { CompanyMgmtDetailComponent } from './company-management-detail.component';
import { CompanyMgmtUpdateComponent } from './company-management-update.component';
import { CompanyRouteAccessService } from './../../core';

@Injectable({ providedIn: 'root' })
export class CompanyMgmtResolve implements Resolve<any> {
    constructor(private service: CompanyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id);
        }
        return new Company();
    }
}

export const companyMgmtRoute: Routes = [
    {
        path: 'company-management',
        component: CompanyMgmtComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            pageTitle: 'companyManagement.home.title'
        },
        canActivate: [CompanyRouteAccessService]
    },
    {
        path: 'company-management/:id/view',
        component: CompanyMgmtDetailComponent,
        resolve: {
            company: CompanyMgmtResolve
        },
        data: {
            pageTitle: 'companyManagement.home.title'
        }
    },
    {
        path: 'company-management/new',
        component: CompanyMgmtUpdateComponent,
        resolve: {
            company: CompanyMgmtResolve
        }
    },
    {
        path: 'company-management/:id/edit',
        component: CompanyMgmtUpdateComponent,
        resolve: {
            company: CompanyMgmtResolve
        }
    }
];
