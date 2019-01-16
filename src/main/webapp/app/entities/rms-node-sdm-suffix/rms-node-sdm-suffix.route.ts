import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';
import { RmsNodeSdmSuffixService } from './rms-node-sdm-suffix.service';
import { RmsNodeSdmSuffixComponent } from './rms-node-sdm-suffix.component';
import { RmsNodeSdmSuffixDetailComponent } from './rms-node-sdm-suffix-detail.component';
import { RmsNodeSdmSuffixUpdateComponent } from './rms-node-sdm-suffix-update.component';
import { RmsNodeSdmSuffixDeletePopupComponent } from './rms-node-sdm-suffix-delete-dialog.component';
import { IRmsNodeSdmSuffix } from 'app/shared/model/rms-node-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class RmsNodeSdmSuffixResolve implements Resolve<IRmsNodeSdmSuffix> {
    constructor(private service: RmsNodeSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RmsNodeSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RmsNodeSdmSuffix>) => response.ok),
                map((rmsNode: HttpResponse<RmsNodeSdmSuffix>) => rmsNode.body)
            );
        }
        return of(new RmsNodeSdmSuffix());
    }
}

export const rmsNodeRoute: Routes = [
    {
        path: 'rms-node-sdm-suffix',
        component: RmsNodeSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsNode.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-node-sdm-suffix/:id/view',
        component: RmsNodeSdmSuffixDetailComponent,
        resolve: {
            rmsNode: RmsNodeSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsNode.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-node-sdm-suffix/new',
        component: RmsNodeSdmSuffixUpdateComponent,
        resolve: {
            rmsNode: RmsNodeSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsNode.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-node-sdm-suffix/:id/edit',
        component: RmsNodeSdmSuffixUpdateComponent,
        resolve: {
            rmsNode: RmsNodeSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsNode.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rmsNodePopupRoute: Routes = [
    {
        path: 'rms-node-sdm-suffix/:id/delete',
        component: RmsNodeSdmSuffixDeletePopupComponent,
        resolve: {
            rmsNode: RmsNodeSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsNode.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
