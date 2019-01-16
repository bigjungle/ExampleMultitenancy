import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';
import { PlanInfoDataSdmSuffixService } from './plan-info-data-sdm-suffix.service';
import { PlanInfoDataSdmSuffixComponent } from './plan-info-data-sdm-suffix.component';
import { PlanInfoDataSdmSuffixDetailComponent } from './plan-info-data-sdm-suffix-detail.component';
import { PlanInfoDataSdmSuffixUpdateComponent } from './plan-info-data-sdm-suffix-update.component';
import { PlanInfoDataSdmSuffixDeletePopupComponent } from './plan-info-data-sdm-suffix-delete-dialog.component';
import { IPlanInfoDataSdmSuffix } from 'app/shared/model/plan-info-data-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoDataSdmSuffixResolve implements Resolve<IPlanInfoDataSdmSuffix> {
    constructor(private service: PlanInfoDataSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoDataSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoDataSdmSuffix>) => response.ok),
                map((planInfoData: HttpResponse<PlanInfoDataSdmSuffix>) => planInfoData.body)
            );
        }
        return of(new PlanInfoDataSdmSuffix());
    }
}

export const planInfoDataRoute: Routes = [
    {
        path: 'plan-info-data-sdm-suffix',
        component: PlanInfoDataSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-sdm-suffix/:id/view',
        component: PlanInfoDataSdmSuffixDetailComponent,
        resolve: {
            planInfoData: PlanInfoDataSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-sdm-suffix/new',
        component: PlanInfoDataSdmSuffixUpdateComponent,
        resolve: {
            planInfoData: PlanInfoDataSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-sdm-suffix/:id/edit',
        component: PlanInfoDataSdmSuffixUpdateComponent,
        resolve: {
            planInfoData: PlanInfoDataSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoDataPopupRoute: Routes = [
    {
        path: 'plan-info-data-sdm-suffix/:id/delete',
        component: PlanInfoDataSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoData: PlanInfoDataSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
