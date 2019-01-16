import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-data-atch-his-sdm-suffix.model';
import { PlanInfoDataAtchHisSdmSuffixService } from './plan-info-data-atch-his-sdm-suffix.service';
import { PlanInfoDataAtchHisSdmSuffixComponent } from './plan-info-data-atch-his-sdm-suffix.component';
import { PlanInfoDataAtchHisSdmSuffixDetailComponent } from './plan-info-data-atch-his-sdm-suffix-detail.component';
import { PlanInfoDataAtchHisSdmSuffixUpdateComponent } from './plan-info-data-atch-his-sdm-suffix-update.component';
import { PlanInfoDataAtchHisSdmSuffixDeletePopupComponent } from './plan-info-data-atch-his-sdm-suffix-delete-dialog.component';
import { IPlanInfoDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-data-atch-his-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoDataAtchHisSdmSuffixResolve implements Resolve<IPlanInfoDataAtchHisSdmSuffix> {
    constructor(private service: PlanInfoDataAtchHisSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoDataAtchHisSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoDataAtchHisSdmSuffix>) => response.ok),
                map((planInfoDataAtchHis: HttpResponse<PlanInfoDataAtchHisSdmSuffix>) => planInfoDataAtchHis.body)
            );
        }
        return of(new PlanInfoDataAtchHisSdmSuffix());
    }
}

export const planInfoDataAtchHisRoute: Routes = [
    {
        path: 'plan-info-data-atch-his-sdm-suffix',
        component: PlanInfoDataAtchHisSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-atch-his-sdm-suffix/:id/view',
        component: PlanInfoDataAtchHisSdmSuffixDetailComponent,
        resolve: {
            planInfoDataAtchHis: PlanInfoDataAtchHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-atch-his-sdm-suffix/new',
        component: PlanInfoDataAtchHisSdmSuffixUpdateComponent,
        resolve: {
            planInfoDataAtchHis: PlanInfoDataAtchHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-atch-his-sdm-suffix/:id/edit',
        component: PlanInfoDataAtchHisSdmSuffixUpdateComponent,
        resolve: {
            planInfoDataAtchHis: PlanInfoDataAtchHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoDataAtchHisPopupRoute: Routes = [
    {
        path: 'plan-info-data-atch-his-sdm-suffix/:id/delete',
        component: PlanInfoDataAtchHisSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoDataAtchHis: PlanInfoDataAtchHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
