import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoDataSdmSuffixComponent,
    PlanInfoDataSdmSuffixDetailComponent,
    PlanInfoDataSdmSuffixUpdateComponent,
    PlanInfoDataSdmSuffixDeletePopupComponent,
    PlanInfoDataSdmSuffixDeleteDialogComponent,
    planInfoDataRoute,
    planInfoDataPopupRoute
} from './';

const ENTITY_STATES = [...planInfoDataRoute, ...planInfoDataPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoDataSdmSuffixComponent,
        PlanInfoDataSdmSuffixDetailComponent,
        PlanInfoDataSdmSuffixUpdateComponent,
        PlanInfoDataSdmSuffixDeleteDialogComponent,
        PlanInfoDataSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoDataSdmSuffixComponent,
        PlanInfoDataSdmSuffixUpdateComponent,
        PlanInfoDataSdmSuffixDeleteDialogComponent,
        PlanInfoDataSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoDataSdmSuffixModule {}
