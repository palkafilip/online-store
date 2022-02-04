from app import app
from persistence.coupons_reader import get_all_coupons, get_active_coupon


@app.route('/coupons', methods=['GET'])
def get_coupons():
    coupons = get_all_coupons()
    return coupons, 200


@app.route('/coupons/active', methods=['GET'])
def get_act_coupon():
    active_coupons = get_active_coupon()
    if active_coupons is None:
        return '{"message": "coupons not found"}', 404
    return active_coupons, 200

