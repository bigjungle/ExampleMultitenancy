import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoDataAtchHisSdmSuffixComponent,
    PlanInfoDataAtchHisSdmSuffixDetailComponent,
    PlanInfoDataAtchHisSdmSuffixUpdateComponent,
    PlanInfoDataAtchHisSdmSuffixDeletePopupComponent,
    PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent,
    planInfoDataAtchHisRoute,
    planInfoDataAtchHisPopupRoute
} from './';

const ENTITY_STATES = [...planInfoDataAtchHisRoute, ...planInfoDataAtchHisPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoDataAtchHisSdmSuffixComponent,
        PlanInfoDataAtchHisSdmSuffixDetailComponent,
        PlanInfoDataAtchHisSdmSuffixUpdateComponent,
        PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent,
        PlanInfoDataAtchHisSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoDataAtchHisSdmSuffixComponent,
        PlanInfoDataAtchHisSdmSuffixUpdateComponent,
        PlanInfoDataAtchHisSdmSuffixDeleteDialogComponent,
        PlanInfoDataAtchHisSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoDataAtchHisSdmSuffixModule {}
