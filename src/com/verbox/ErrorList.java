/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verbox;

/**
 *
 * @author maxxl
 */
public class ErrorList {

    /**
     *
     * @param str
     * @return
     */
    public static String DesctiptError(String str)
    {
     switch (str) {
                
                case "not_action_name": return "Нет такого АПИ метода.";                
                case "not_cash_id": return "Нет такой кассы.";
                case "not_cashier_id": return "Нет такого кассира.";
                case "not_cash_password": return "Агааа!!! Хочешь запустить программу? А не получится!!!.";
                case "not_cashier_password": return "Неправельный пароль кассира.";
                case "not_params": return "Отсутствует Params на входе.";
                case "not_lastmes": return "Проверь дату последнего сообщения lastmes.";
                case "not_cash": return "Отсутствует данная каса.";
                case "not_cashier" : return "Отсутствует данный кассир.";
                case "openday_already_set": return "День уже открыт.";
                case "not_currency_order": return "Нет приказа по валютам на сегодня. (Если вы создали приказ после запуска программы, есть вероятность не получить курсы валют.)";
                case "empty_params": return "Пустой Params.";
                case "not_currency_code": return "Не существующий код валюты.";
                case "auto_close_day": return "Произошло автозакрытие дня. Пакуйте вёсла.";
                case "not_openday_now": return "Не открыт день.";
                case "closeday_already_set": return "День по кассе уже закрыт.";
                case "not_currency_course": return "Несуществующий курс валюты.";
                case "not_currency_sum": return "Несуществующая сумма валюты.";
                case "not_grn_sum": return "Несуществующая сумма в гривне.";
                case "not_taxpf": return "Не корректен налог на ПФ.";
                case "not_receipt_currency": return "Несуществующая квитанция по валюте.";
                case "not_buyer_resident": return "Резидент не резидент.";
                case "not_cartulary_id": return "Не существует id операции в массиве (params).";
                case "not_type": return "Не существует тип операции в массиве (params)  .";
                case "not_receipt_number": return "Не существует номер квитанции в массиве (params для подкрепления и инкассации).";
                case "not_grn_cash": return "Нет (не хватает) гривны в кассе.";
                case "not_currency_cash": return "Нет (не хватает) валюты в кассе.";
                case "not_valid_taxpf_buy": return "Не действительный налог на покупку ПФ.";
                case "not_valid_taxpf_sale": return "Не действительный налог на продажу ПФ.";
                case "not_valid_course_buy": return "Не действительный курс на покупку валюты (согласно приказа).";
                case "not_valid_course_sale": return "Не действительный курс на продажу валюты (согласно приказа).";
                case "not_count_grn_sum": return "Не правильно подсчитана сумма в гривне (операции покупка/продажа).";
                case "not_valid_receipt_currency": return "Не валидный по счету номер квитанции по валюте (операции покупка/продажа/сторно). Номер должен каждый день начинаться с цифры 1.";
                case "not_valid_receipt_number": return "Не валидный по счету номер квитанции (операции подкрепление/инкассация). Номер должен каждый день начинаться с цифры 1.";
                case "not_record_cartulary": return "Нет записи в базе согласно присланному приложением типу операции (операции удаления/сторно).";
                case "not_valid_cash_id": return "Не совпадает id кассы с id кассы на сервере (операции удаления/сторно).";
                case "not_valid_cartulary_id": return "Не совпадает id операции присланный приложением в массиве (params) с id операции на сервере (операции удаления/сторно).";
                case "not_valid_currency_code": return "Не совпадает код валюты присланный приложением в массиве (params) с кодом валюты на сервере (операции удаления/сторно).";
                case "not_valid_currency_sum": return "Не совпадает cсумма валюты присланная приложением в массиве (params) с суммой валюты на сервере (операции удаления/сторно).";
                case "not_valid_grn_sum": return "Не совпадает сумма в гривне присланная приложением в массиве (params) с суммой гривны на сервере (операции удаления/сторно).";
                case "not_rights_delete": return "Нет прав на удаление (операции удаления/сторно).";
                case "not_delete_replenish": return "нельзя удалить подкрепление (в случае если остаток после удаление в минусе).";
               
    }
    
    return str;
    }
}
