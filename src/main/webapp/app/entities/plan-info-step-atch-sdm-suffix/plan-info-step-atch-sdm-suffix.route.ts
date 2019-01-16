import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';
import { PlanInfoStepAtchSdmSuffixService } from './plan-info-step-atch-sdm-suffix.service';
import { PlanInfoStepAtchSdmSuffixComponent } from './plan-info-step-atch-sdm-suffix.component';
import { PlanInfoStepAtchSdmSuffixDetailComponent } from './plan-info-step-atch-sdm-suffix-detail.component';
import { PlanInfoStepAtchSdmSuffixUpdateComponent } from './plan-info-step-atch-sdm-suffix-update.component';
import { PlanInfoStepAtchSdmSuffixDeletePopupComponent } from './plan-info-step-atch-sdm-suffix-delete-dialog.component';
import { IPlanInfoStepAtchSdmSuffix } from 'app/shared/model/plan-info-step-atch-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoStepAtchSdmSuffixResolve implements Resolve<IPlanInfoStepAtchSdmSuffix> {
    constructor(private service: PlanInfoStepAtchSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoStepAtchSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoStepAtchSdmSuffix>) => response.ok),
                map((planInfoStepAtch: HttpResponse<PlanInfoStepAtchSdmSuffix>) => planInfoStepAtch.body)
            );
        }
        return of(new PlanInfoStepAtchSdmSuffix());
    }
}

export const planInfoStepAtchRoute: Routes = [
    {
        path: 'plan-info-step-atch-sdm-suffix',
        component: PlanInfoStepAtchSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-atch-sdm-suffix/:id/view',
        component: PlanInfoStepAtchSdmSuffixDetailComponent,
        resolve: {
            planInfoStepAtch: PlanInfoStepAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-atch-sdm-suffix/new',
        component: PlanInfoStepAtchSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepAtch: PlanInfoStepAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-atch-sdm-suffix/:id/edit',
        component: PlanInfoStepAtchSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepAtch: PlanInfoStepAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoStepAtchPopupRoute: Routes = [
    {
        path: 'plan-info-step-atch-sdm-suffix/:id/delete',
        component: PlanInfoStepAtchSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoStepAtch: PlanInfoStepAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepAtch.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
