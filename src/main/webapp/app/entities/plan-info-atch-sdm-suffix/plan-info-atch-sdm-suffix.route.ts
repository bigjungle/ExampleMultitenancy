import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';
import { PlanInfoAtchSdmSuffixService } from './plan-info-atch-sdm-suffix.service';
import { PlanInfoAtchSdmSuffixComponent } from './plan-info-atch-sdm-suffix.component';
import { PlanInfoAtchSdmSuffixDetailComponent } from './plan-info-atch-sdm-suffix-detail.component';
import { PlanInfoAtchSdmSuffixUpdateComponent } from './plan-info-atch-sdm-suffix-update.component';
import { PlanInfoAtchSdmSuffixDeletePopupComponent } from './plan-info-atch-sdm-suffix-delete-dialog.component';
import { IPlanInfoAtchSdmSuffix } from 'app/shared/model/plan-info-atch-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoAtchSdmSuffixResolve implements Resolve<IPlanInfoAtchSdmSuffix> {
    constructor(private service: PlanInfoAtchSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoAtchSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoAtchSdmSuffix>) => response.ok),
                map((planInfoAtch: HttpResponse<PlanInfoAtchSdmSuffix>) => planInfoAtch.body)
            );
        }
        return of(new PlanInfoAtchSdmSuffix());
    }
}

export const planInfoAtchRoute: Routes = [
    {
        path: 'plan-info-atch-sdm-suffix',
        component: PlanInfoAtchSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-atch-sdm-suffix/:id/view',
        component: PlanInfoAtchSdmSuffixDetailComponent,
        resolve: {
            planInfoAtch: PlanInfoAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-atch-sdm-suffix/new',
        component: PlanInfoAtchSdmSuffixUpdateComponent,
        resolve: {
            planInfoAtch: PlanInfoAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-atch-sdm-suffix/:id/edit',
        component: PlanInfoAtchSdmSuffixUpdateComponent,
        resolve: {
            planInfoAtch: PlanInfoAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoAtch.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoAtchPopupRoute: Routes = [
    {
        path: 'plan-info-atch-sdm-suffix/:id/delete',
        component: PlanInfoAtchSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoAtch: PlanInfoAtchSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoAtch.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
