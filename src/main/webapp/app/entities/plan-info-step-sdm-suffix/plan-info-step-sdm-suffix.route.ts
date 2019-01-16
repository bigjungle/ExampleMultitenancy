import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';
import { PlanInfoStepSdmSuffixService } from './plan-info-step-sdm-suffix.service';
import { PlanInfoStepSdmSuffixComponent } from './plan-info-step-sdm-suffix.component';
import { PlanInfoStepSdmSuffixDetailComponent } from './plan-info-step-sdm-suffix-detail.component';
import { PlanInfoStepSdmSuffixUpdateComponent } from './plan-info-step-sdm-suffix-update.component';
import { PlanInfoStepSdmSuffixDeletePopupComponent } from './plan-info-step-sdm-suffix-delete-dialog.component';
import { IPlanInfoStepSdmSuffix } from 'app/shared/model/plan-info-step-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoStepSdmSuffixResolve implements Resolve<IPlanInfoStepSdmSuffix> {
    constructor(private service: PlanInfoStepSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoStepSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoStepSdmSuffix>) => response.ok),
                map((planInfoStep: HttpResponse<PlanInfoStepSdmSuffix>) => planInfoStep.body)
            );
        }
        return of(new PlanInfoStepSdmSuffix());
    }
}

export const planInfoStepRoute: Routes = [
    {
        path: 'plan-info-step-sdm-suffix',
        component: PlanInfoStepSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-sdm-suffix/:id/view',
        component: PlanInfoStepSdmSuffixDetailComponent,
        resolve: {
            planInfoStep: PlanInfoStepSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-sdm-suffix/new',
        component: PlanInfoStepSdmSuffixUpdateComponent,
        resolve: {
            planInfoStep: PlanInfoStepSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-sdm-suffix/:id/edit',
        component: PlanInfoStepSdmSuffixUpdateComponent,
        resolve: {
            planInfoStep: PlanInfoStepSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStep.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoStepPopupRoute: Routes = [
    {
        path: 'plan-info-step-sdm-suffix/:id/delete',
        component: PlanInfoStepSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoStep: PlanInfoStepSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStep.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
