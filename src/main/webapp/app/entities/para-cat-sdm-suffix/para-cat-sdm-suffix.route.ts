import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';
import { ParaCatSdmSuffixService } from './para-cat-sdm-suffix.service';
import { ParaCatSdmSuffixComponent } from './para-cat-sdm-suffix.component';
import { ParaCatSdmSuffixDetailComponent } from './para-cat-sdm-suffix-detail.component';
import { ParaCatSdmSuffixUpdateComponent } from './para-cat-sdm-suffix-update.component';
import { ParaCatSdmSuffixDeletePopupComponent } from './para-cat-sdm-suffix-delete-dialog.component';
import { IParaCatSdmSuffix } from 'app/shared/model/para-cat-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class ParaCatSdmSuffixResolve implements Resolve<IParaCatSdmSuffix> {
    constructor(private service: ParaCatSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ParaCatSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ParaCatSdmSuffix>) => response.ok),
                map((paraCat: HttpResponse<ParaCatSdmSuffix>) => paraCat.body)
            );
        }
        return of(new ParaCatSdmSuffix());
    }
}

export const paraCatRoute: Routes = [
    {
        path: 'para-cat-sdm-suffix',
        component: ParaCatSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraCat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-cat-sdm-suffix/:id/view',
        component: ParaCatSdmSuffixDetailComponent,
        resolve: {
            paraCat: ParaCatSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraCat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-cat-sdm-suffix/new',
        component: ParaCatSdmSuffixUpdateComponent,
        resolve: {
            paraCat: ParaCatSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraCat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-cat-sdm-suffix/:id/edit',
        component: ParaCatSdmSuffixUpdateComponent,
        resolve: {
            paraCat: ParaCatSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraCat.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paraCatPopupRoute: Routes = [
    {
        path: 'para-cat-sdm-suffix/:id/delete',
        component: ParaCatSdmSuffixDeletePopupComponent,
        resolve: {
            paraCat: ParaCatSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraCat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
