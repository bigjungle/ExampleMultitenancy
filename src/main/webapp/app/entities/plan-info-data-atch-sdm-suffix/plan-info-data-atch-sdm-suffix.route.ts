import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoDataAtchSdmSuffix } from 'app/shared/model/plan-info-data-atch-sdm-suffix.model';
import { PlanInfoDataAtchSdmSuffixService } from './plan-info-data-atch-sdm-suffix.service';
import { PlanInfoDataAtchSdmSuffixComponent } from './plan-info-data-atch-sdm-suffix.component';
import { PlanInfoDataAtchSdmSuffixDetailComponent } from './plan-info-data-atch-sdm-suffix-detail.component';
import { PlanInfoDataAtchSdmSuffixUpdateComponent } from './plan-info-data-atch-sdm-suffix-update.component';
import { PlanInfoDataAtchSdmSuffixDeletePopupComponent } from './plan-info-data-atch-sdm-suffix-delete-dialog.component';
import { IPlanInfoDataAtchSdmSuffix } from 'app/shared/model/plan-info-data-atch-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoDataAtchSdmSuffixResolve implements Resolve<IPlanInfoDataAtchSdmSuffix> {
    constructor(private service: PlanInfoDataAtchSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoDataAtchSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoDataAtchSdmSuffix>) => response.ok),
                map((planInfoDataAtch: HttpResponse<PlanInfoDataAtchSdmSuffix>) => planInfoDataAtch.body)
            );
        }
        return of(new PlanInfoDataAtchSdmSuffix());
    }
}

export const planInfoDataAtchRoute: Routes = [
    {
        path: 'plan-info-data-atch-sdm-suffix',
        component: PlanInfoDataAtchSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-atch-sdm-suffix/:id/view',
        component: PlanInfoDataAtchSdmSuffixDetailComponent,
        resolve: {
            planInfoDataAtch: PlanInfoDataAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-atch-sdm-suffix/new',
        component: PlanInfoDataAtchSdmSuffixUpdateComponent,
        resolve: {
            planInfoDataAtch: PlanInfoDataAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-data-atch-sdm-suffix/:id/edit',
        component: PlanInfoDataAtchSdmSuffixUpdateComponent,
        resolve: {
            planInfoDataAtch: PlanInfoDataAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoDataAtchPopupRoute: Routes = [
    {
        path: 'plan-info-data-atch-sdm-suffix/:id/delete',
        component: PlanInfoDataAtchSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoDataAtch: PlanInfoDataAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
