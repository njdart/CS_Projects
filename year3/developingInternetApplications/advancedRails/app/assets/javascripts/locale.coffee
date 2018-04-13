# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/
SUBMIT_LOCALE_ID = '#locale-submit'
AUTOFOCUS = "[autofocus]"

$(document).on "ready page:change", ->
  $(SUBMIT_LOCALE_ID).hide()
  $(AUTOFOCUS).focus()
