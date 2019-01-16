import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';
import { PlanInfoDataHisSdmSuffixService } from './plan-info-data-his-sdm-suffix.service';
import { PlanInfoDataHisSdmSuffixComponent } from './plan-info-data-his-sdm-suffix.component';
import { PlanInfoDataHisSdmSuffixDetailComponent } from './plan-info-data-his-sdm-suffix-detail.component';
import { PlanInfoDataHisSdmSuffixUpdateComponent } from './plan-info-data-his-sdm-suffix-update.component';
import { PlanInfoDataHisSdmSuffixDeletePopupComponent } from './plan-info-data-his-sdm-suffix-delete-dialog.component';
import { IPlanInfoDataHisSdmSuffix } from 'app/shared/model/plan-info-data-his-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoDataHisSdmSuffixResolve implements Resolve<IPlanInfoDataHisSdmSuffix> {
    constructor(private service: PlanInfoDataHisSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoDataHisSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoDataHisSdmSuffix>) => response.ok),
                map((planInfoDataHis: HttpResponse<PlanInfoDataHisSdmSuffix>) => planInfoDataHis.body)
            );
        }
        return of(new PlanInfoDataHisSdmSuffix());
    }
}

export const planInfoDataHisRoute: Routes = [
    {
        path: 'plan-info-data-his-sdm-suffix',
        component: PlanInfoDataHisSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-his-sdm-suffix/:id/view',
        component: PlanInfoDataHisSdmSuffixDetailComponent,
        resolve: {
            planInfoDataHis: PlanInfoDataHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-his-sdm-suffix/new',
        component: PlanInfoDataHisSdmSuffixUpdateComponent,
        resolve: {
            planInfoDataHis: PlanInfoDataHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-his-sdm-suffix/:id/edit',
        component: PlanInfoDataHisSdmSuffixUpdateComponent,
        resolve: {
            planInfoDataHis: PlanInfoDataHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoDataHisPopupRoute: Routes = [
    {
        path: 'plan-info-data-his-sdm-suffix/:id/delete',
        component: PlanInfoDataHisSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoDataHis: PlanInfoDataHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataHis.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
