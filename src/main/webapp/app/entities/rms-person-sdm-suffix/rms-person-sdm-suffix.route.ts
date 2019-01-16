import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RmsPersonSdmSuffix } from 'app/shared/model/rms-person-sdm-suffix.model';
import { RmsPersonSdmSuffixService } from './rms-person-sdm-suffix.service';
import { RmsPersonSdmSuffixComponent } from './rms-person-sdm-suffix.component';
import { RmsPersonSdmSuffixDetailComponent } from './rms-person-sdm-suffix-detail.component';
import { RmsPersonSdmSuffixUpdateComponent } from './rms-person-sdm-suffix-update.component';
import { RmsPersonSdmSuffixDeletePopupComponent } from './rms-person-sdm-suffix-delete-dialog.component';
import { IRmsPersonSdmSuffix } from 'app/shared/model/rms-person-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class RmsPersonSdmSuffixResolve implements Resolve<IRmsPersonSdmSuffix> {
    constructor(private service: RmsPersonSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RmsPersonSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RmsPersonSdmSuffix>) => response.ok),
                map((rmsPerson: HttpResponse<RmsPersonSdmSuffix>) => rmsPerson.body)
            );
        }
        return of(new RmsPersonSdmSuffix());
    }
}

export const rmsPersonRoute: Routes = [
    {
        path: 'rms-person-sdm-suffix',
        component: RmsPersonSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsPerson.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-person-sdm-suffix/:id/view',
        component: RmsPersonSdmSuffixDetailComponent,
        resolve: {
            rmsPerson: RmsPersonSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsPerson.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-person-sdm-suffix/new',
        component: RmsPersonSdmSuffixUpdateComponent,
        resolve: {
            rmsPerson: RmsPersonSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsPerson.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-person-sdm-suffix/:id/edit',
        component: RmsPersonSdmSuffixUpdateComponent,
        resolve: {
            rmsPerson: RmsPersonSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsPerson.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rmsPersonPopupRoute: Routes = [
    {
        path: 'rms-person-sdm-suffix/:id/delete',
        component: RmsPersonSdmSuffixDeletePopupComponent,
        resolve: {
            rmsPerson: RmsPersonSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsPerson.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
