import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    RmsUserSdmSuffixComponent,
    RmsUserSdmSuffixDetailComponent,
    RmsUserSdmSuffixUpdateComponent,
    RmsUserSdmSuffixDeletePopupComponent,
    RmsUserSdmSuffixDeleteDialogComponent,
    rmsUserRoute,
    rmsUserPopupRoute
} from './';

const ENTITY_STATES = [...rmsUserRoute, ...rmsUserPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RmsUserSdmSuffixComponent,
        RmsUserSdmSuffixDetailComponent,
        RmsUserSdmSuffixUpdateComponent,
        RmsUserSdmSuffixDeleteDialogComponent,
        RmsUserSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        RmsUserSdmSuffixComponent,
        RmsUserSdmSuffixUpdateComponent,
        RmsUserSdmSuffixDeleteDialogComponent,
        RmsUserSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbRmsUserSdmSuffixModule {}
