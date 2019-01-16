import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoDataAtchSdmSuffixComponent,
    PlanInfoDataAtchSdmSuffixDetailComponent,
    PlanInfoDataAtchSdmSuffixUpdateComponent,
    PlanInfoDataAtchSdmSuffixDeletePopupComponent,
    PlanInfoDataAtchSdmSuffixDeleteDialogComponent,
    planInfoDataAtchRoute,
    planInfoDataAtchPopupRoute
} from './';

const ENTITY_STATES = [...planInfoDataAtchRoute, ...planInfoDataAtchPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoDataAtchSdmSuffixComponent,
        PlanInfoDataAtchSdmSuffixDetailComponent,
        PlanInfoDataAtchSdmSuffixUpdateComponent,
        PlanInfoDataAtchSdmSuffixDeleteDialogComponent,
        PlanInfoDataAtchSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoDataAtchSdmSuffixComponent,
        PlanInfoDataAtchSdmSuffixUpdateComponent,
        PlanInfoDataAtchSdmSuffixDeleteDialogComponent,
        PlanInfoDataAtchSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoDataAtchSdmSuffixModule {}
