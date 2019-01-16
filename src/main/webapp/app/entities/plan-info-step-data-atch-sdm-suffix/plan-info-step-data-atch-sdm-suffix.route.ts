import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoStepDataAtchSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-sdm-suffix.model';
import { PlanInfoStepDataAtchSdmSuffixService } from './plan-info-step-data-atch-sdm-suffix.service';
import { PlanInfoStepDataAtchSdmSuffixComponent } from './plan-info-step-data-atch-sdm-suffix.component';
import { PlanInfoStepDataAtchSdmSuffixDetailComponent } from './plan-info-step-data-atch-sdm-suffix-detail.component';
import { PlanInfoStepDataAtchSdmSuffixUpdateComponent } from './plan-info-step-data-atch-sdm-suffix-update.component';
import { PlanInfoStepDataAtchSdmSuffixDeletePopupComponent } from './plan-info-step-data-atch-sdm-suffix-delete-dialog.component';
import { IPlanInfoStepDataAtchSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoStepDataAtchSdmSuffixResolve implements Resolve<IPlanInfoStepDataAtchSdmSuffix> {
    constructor(private service: PlanInfoStepDataAtchSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoStepDataAtchSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoStepDataAtchSdmSuffix>) => response.ok),
                map((planInfoStepDataAtch: HttpResponse<PlanInfoStepDataAtchSdmSuffix>) => planInfoStepDataAtch.body)
            );
        }
        return of(new PlanInfoStepDataAtchSdmSuffix());
    }
}

export const planInfoStepDataAtchRoute: Routes = [
    {
        path: 'plan-info-step-data-atch-sdm-suffix',
        component: PlanInfoStepDataAtchSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-atch-sdm-suffix/:id/view',
        component: PlanInfoStepDataAtchSdmSuffixDetailComponent,
        resolve: {
            planInfoStepDataAtch: PlanInfoStepDataAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-atch-sdm-suffix/new',
        component: PlanInfoStepDataAtchSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepDataAtch: PlanInfoStepDataAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-atch-sdm-suffix/:id/edit',
        component: PlanInfoStepDataAtchSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepDataAtch: PlanInfoStepDataAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoStepDataAtchPopupRoute: Routes = [
    {
        path: 'plan-info-step-data-atch-sdm-suffix/:id/delete',
        component: PlanInfoStepDataAtchSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoStepDataAtch: PlanInfoStepDataAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtch.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
