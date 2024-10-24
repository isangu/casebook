import React from 'react';
import DatePicker, { registerLocale } from 'react-datepicker';
import PropTypes from 'prop-types';
import ko from 'date-fns/locale/ko';
import en from 'date-fns/locale/en-US';

import { DATE_FORMATS } from '@/constants/constants';
import DateCustomInput from '@/components/DateRange/DateCustomInput/DateCustomInput';
import dateUtil from '@/utils/dateUtil';
import Liner from '@/components/Liner/Liner';
import './DateRange.scss';

registerLocale('ko', ko);
registerLocale('en', en);

function DateRange({ className, language, startDate, endDate, outline, onChange, size, showTimeSelect, showTimeSelectOnly, startDateKey, endDateKey }) {
  return (
    <div className={`date-range-wrapper ${className} size-${size} ${outline ? 'outline' : ''}`}>
      <div>
        <DatePicker
          className="date-picker start-date-picker"
          selected={startDate ? new Date(startDate) : null}
          showTimeSelect={showTimeSelect}
          showTimeSelectOnly={showTimeSelectOnly}
          onChange={date => {
            onChange(startDateKey, date.getTime());
          }}
          locale={language}
          customInput={<DateCustomInput />}
          dateFormat={DATE_FORMATS[dateUtil.getUserLocale()][showTimeSelectOnly ? 'hoursMinutes' : 'full'].picker}
        />
      </div>
      <Liner className="dash" width="10px" height="1px" display="inline-block" color="black" margin="0 0.75rem 0 0.5rem" />
      <div>
        <DatePicker
          className="date-picker end-date-picker"
          selected={endDate ? new Date(endDate) : null}
          showTimeSelect={showTimeSelect}
          showTimeSelectOnly={showTimeSelectOnly}
          onChange={date => {
            onChange(endDateKey, date.getTime());
          }}
          locale={language}
          customInput={<DateCustomInput />}
          dateFormat={DATE_FORMATS[dateUtil.getUserLocale()][showTimeSelectOnly ? 'hoursMinutes' : 'full'].picker}
        />
      </div>
    </div>
  );
}

export default DateRange;

DateRange.defaultProps = {
  className: '',
  size: 'md',
  showTimeSelect: true,
  showTimeSelectOnly: false,
  startDateKey: 'startDate',
  endDateKey: 'endDate',
  language: '',
  startDate: null,
  endDate: null,
  onChange: null,
  outline: true,
};

DateRange.propTypes = {
  className: PropTypes.string,
  language: PropTypes.string,
  startDate: PropTypes.number,
  endDate: PropTypes.number,
  onChange: PropTypes.func,
  size: PropTypes.string,
  showTimeSelect: PropTypes.bool,
  showTimeSelectOnly: PropTypes.bool,
  startDateKey: PropTypes.string,
  endDateKey: PropTypes.string,
  outline: PropTypes.bool,
};
