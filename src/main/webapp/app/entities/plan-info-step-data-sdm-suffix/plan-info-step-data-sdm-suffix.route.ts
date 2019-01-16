import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';
import { PlanInfoStepDataSdmSuffixService } from './plan-info-step-data-sdm-suffix.service';
import { PlanInfoStepDataSdmSuffixComponent } from './plan-info-step-data-sdm-suffix.component';
import { PlanInfoStepDataSdmSuffixDetailComponent } from './plan-info-step-data-sdm-suffix-detail.component';
import { PlanInfoStepDataSdmSuffixUpdateComponent } from './plan-info-step-data-sdm-suffix-update.component';
import { PlanInfoStepDataSdmSuffixDeletePopupComponent } from './plan-info-step-data-sdm-suffix-delete-dialog.component';
import { IPlanInfoStepDataSdmSuffix } from 'app/shared/model/plan-info-step-data-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoStepDataSdmSuffixResolve implements Resolve<IPlanInfoStepDataSdmSuffix> {
    constructor(private service: PlanInfoStepDataSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoStepDataSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoStepDataSdmSuffix>) => response.ok),
                map((planInfoStepData: HttpResponse<PlanInfoStepDataSdmSuffix>) => planInfoStepData.body)
            );
        }
        return of(new PlanInfoStepDataSdmSuffix());
    }
}

export const planInfoStepDataRoute: Routes = [
    {
        path: 'plan-info-step-data-sdm-suffix',
        component: PlanInfoStepDataSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-sdm-suffix/:id/view',
        component: PlanInfoStepDataSdmSuffixDetailComponent,
        resolve: {
            planInfoStepData: PlanInfoStepDataSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-sdm-suffix/new',
        component: PlanInfoStepDataSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepData: PlanInfoStepDataSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-sdm-suffix/:id/edit',
        component: PlanInfoStepDataSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepData: PlanInfoStepDataSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoStepDataPopupRoute: Routes = [
    {
        path: 'plan-info-step-data-sdm-suffix/:id/delete',
        component: PlanInfoStepDataSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoStepData: PlanInfoStepDataSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
