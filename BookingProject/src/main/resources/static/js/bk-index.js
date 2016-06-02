/**
 * Created by MUHAIMAN-HENG on 2/23/2016 AD.
 */
$(document).ready(function () {

    $('#start_datepicker').datepicker({
        format: "dd/mm/yyyy",
        weekStart: 1,
        startDate: "-",
        todayBtn: "linked",
        language: "th",
        orientation: "bottom auto",
        daysOfWeekHighlighted: "0",
        autoclose: true,
        todayHighlight: true
    }).datepicker('setDate', '0')
        .on('changeDate', function (e) {
            if ($('#end_datepicker').datepicker('getDate') < $('#start_datepicker').datepicker('getDate')) {
                var d = $('#start_datepicker').datepicker('getDate');
                d.setDate(d.getDate() + 1); // Add three days
                $('#end_datepicker').datepicker('setDate', d);
            }
        });

    $('#end_datepicker').datepicker({
        format: "dd/mm/yyyy",
        weekStart: 1,
        startDate: "+1d",
        todayBtn: true,
        language: "th",
        orientation: "bottom auto",
        daysOfWeekHighlighted: "0",
        autoclose: true,
        todayHighlight: true
    }).datepicker('setDate', "+1d")
        .on('changeDate', function (e) {
            if ($('#start_datepicker').datepicker('getDate') > $('#end_datepicker').datepicker('getDate')) {
                var d = $('#end_datepicker').datepicker('getDate');
                d.setDate(d.getDate() - 1); // Add three days
                $('#start_datepicker').datepicker('setDate', d);
            }
        });


});