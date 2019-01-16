import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoDataHisSdmSuffixComponent,
    PlanInfoDataHisSdmSuffixDetailComponent,
    PlanInfoDataHisSdmSuffixUpdateComponent,
    PlanInfoDataHisSdmSuffixDeletePopupComponent,
    PlanInfoDataHisSdmSuffixDeleteDialogComponent,
    planInfoDataHisRoute,
    planInfoDataHisPopupRoute
} from './';

const ENTITY_STATES = [...planInfoDataHisRoute, ...planInfoDataHisPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoDataHisSdmSuffixComponent,
        PlanInfoDataHisSdmSuffixDetailComponent,
        PlanInfoDataHisSdmSuffixUpdateComponent,
        PlanInfoDataHisSdmSuffixDeleteDialogComponent,
        PlanInfoDataHisSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoDataHisSdmSuffixComponent,
        PlanInfoDataHisSdmSuffixUpdateComponent,
        PlanInfoDataHisSdmSuffixDeleteDialogComponent,
        PlanInfoDataHisSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoDataHisSdmSuffixModule {}
