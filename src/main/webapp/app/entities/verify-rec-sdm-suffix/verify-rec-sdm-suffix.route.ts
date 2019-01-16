import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';
import { VerifyRecSdmSuffixService } from './verify-rec-sdm-suffix.service';
import { VerifyRecSdmSuffixComponent } from './verify-rec-sdm-suffix.component';
import { VerifyRecSdmSuffixDetailComponent } from './verify-rec-sdm-suffix-detail.component';
import { VerifyRecSdmSuffixUpdateComponent } from './verify-rec-sdm-suffix-update.component';
import { VerifyRecSdmSuffixDeletePopupComponent } from './verify-rec-sdm-suffix-delete-dialog.component';
import { IVerifyRecSdmSuffix } from 'app/shared/model/verify-rec-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class VerifyRecSdmSuffixResolve implements Resolve<IVerifyRecSdmSuffix> {
    constructor(private service: VerifyRecSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VerifyRecSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<VerifyRecSdmSuffix>) => response.ok),
                map((verifyRec: HttpResponse<VerifyRecSdmSuffix>) => verifyRec.body)
            );
        }
        return of(new VerifyRecSdmSuffix());
    }
}

export const verifyRecRoute: Routes = [
    {
        path: 'verify-rec-sdm-suffix',
        component: VerifyRecSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.verifyRec.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'verify-rec-sdm-suffix/:id/view',
        component: VerifyRecSdmSuffixDetailComponent,
        resolve: {
            verifyRec: VerifyRecSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.verifyRec.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'verify-rec-sdm-suffix/new',
        component: VerifyRecSdmSuffixUpdateComponent,
        resolve: {
            verifyRec: VerifyRecSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.verifyRec.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'verify-rec-sdm-suffix/:id/edit',
        component: VerifyRecSdmSuffixUpdateComponent,
        resolve: {
            verifyRec: VerifyRecSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.verifyRec.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const verifyRecPopupRoute: Routes = [
    {
        path: 'verify-rec-sdm-suffix/:id/delete',
        component: VerifyRecSdmSuffixDeletePopupComponent,
        resolve: {
            verifyRec: VerifyRecSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.verifyRec.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
