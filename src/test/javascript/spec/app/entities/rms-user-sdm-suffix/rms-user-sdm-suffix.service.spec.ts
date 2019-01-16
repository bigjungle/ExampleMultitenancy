/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { RmsUserSdmSuffixService } from 'app/entities/rms-user-sdm-suffix/rms-user-sdm-suffix.service';
import { IRmsUserSdmSuffix, RmsUserSdmSuffix, ValidType } from 'app/shared/model/rms-user-sdm-suffix.model';

describe('Service Tests', () => {
    describe('RmsUserSdmSuffix Service', () => {
        let injector: TestBed;
        let service: RmsUserSdmSuffixService;
        let httpMock: HttpTestingController;
        let elemDefault: IRmsUserSdmSuffix;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(RmsUserSdmSuffixService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new RmsUserSdmSuffix(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                false,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                ValidType.LONG,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a RmsUserSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new RmsUserSdmSuffix(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a RmsUserSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        userName: 'BBBBBB',
                        personId: 'BBBBBB',
                        userPassword: 'BBBBBB',
                        processPassword: 'BBBBBB',
                        userSort: 'BBBBBB',
                        userDesc: 'BBBBBB',
                        userPasswordValiInstantTimes: 1,
                        userPasswordLockFlag: true,
                        procPasswordValiInstantTimes: 1,
                        procPasswordLockFlag: 'BBBBBB',
                        userProp: 'BBBBBB',
                        by01: 'BBBBBB',
                        by02: 'BBBBBB',
                        by03: 'BBBBBB',
                        by04: 'BBBBBB',
                        by05: 'BBBBBB',
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT),
                        serialNumber: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of RmsUserSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        userName: 'BBBBBB',
                        personId: 'BBBBBB',
                        userPassword: 'BBBBBB',
                        processPassword: 'BBBBBB',
                        userSort: 'BBBBBB',
                        userDesc: 'BBBBBB',
                        userPasswordValiInstantTimes: 1,
                        userPasswordLockFlag: true,
                        procPasswordValiInstantTimes: 1,
                        procPasswordLockFlag: 'BBBBBB',
                        userProp: 'BBBBBB',
                        by01: 'BBBBBB',
                        by02: 'BBBBBB',
                        by03: 'BBBBBB',
                        by04: 'BBBBBB',
                        by05: 'BBBBBB',
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT),
                        serialNumber: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a RmsUserSdmSuffix', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
