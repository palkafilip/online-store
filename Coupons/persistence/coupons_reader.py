import datetime
import json

from utils.constants import DATABASE_PATH


def get_all_coupons():
    return __get_data_from_file(DATABASE_PATH)


def get_active_coupon():
    data = __get_data_from_file(DATABASE_PATH)
    current_date = datetime.date.today()

    for coupon in data['coupons']:
        start_date = __convert_date_from_str(coupon['discountDateStart'])
        end_date = __convert_date_from_str(coupon['discountDateEnd'])
        if start_date <= current_date <= end_date:
            return coupon

    return None


def __get_data_from_file(filename):
    file = open(filename)
    data = json.load(file)
    return data


def __convert_date_from_str(date):
    return datetime.datetime.strptime(date, '%d.%m.%Y').date()
