package my.restaurant.controller;

import jakarta.validation.Valid;
import my.restaurant.dto.AddressDTO;
import my.restaurant.dto.AddressModalDTO;
import my.restaurant.modal.forms.AddressForm;
import my.restaurant.modal.forms.EditAddressForm;
import my.restaurant.service.AddressService;
import my.restaurant.service.CountryService;
import my.restaurant.service.StateService;
import my.restaurant.utils.Constants;
import my.restaurant.utils.RestaurantUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class AddressController {

    private final AddressService addressService;
    private final CountryService countryService;
    private final StateService stateService;
    private static final String COUNTRIES_ATTRIBUTE = "countries";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String STATES_ATTRIBUTE = "states";
    private static final String REDIRECT_ATTRIBUTE = "redirect:/user/addresses";

    public AddressController(AddressService addressService, CountryService countryService, StateService stateService) {
        this.addressService = addressService;
        this.countryService = countryService;
        this.stateService = stateService;
    }

    @GetMapping("/user/addresses")
    @PreAuthorize("hasAuthority('USER')")
    public String addresses(ModelMap modelMap) {
        modelMap.addAttribute(Constants.PageTitle, "List of addresses");
        modelMap.addAttribute("addresses", this.addressService.getAddressesForUser());
        return "user/address/addressList";
    }

    @GetMapping("user/address/add")
    @PreAuthorize("hasAuthority('USER')")
    public String addAddress(Model model) {
        model.addAttribute(Constants.PageTitle, "Add Address");
        model.addAttribute("addressForm", new AddressForm());
        model.addAttribute(COUNTRIES_ATTRIBUTE, this.countryService.getCountries());
        return "user/address/addAddress";
    }

    @PostMapping("user/address/add")
    @PreAuthorize("hasAuthority('USER')")
    public String addAddress(@Valid @ModelAttribute("addressForm") AddressForm addressForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        model.addAttribute(Constants.PageTitle, "Add Address");
        model.addAttribute(COUNTRIES_ATTRIBUTE, this.countryService.getCountries());
        if (addressForm.getCountry() != null) {
            model.addAttribute(STATES_ATTRIBUTE, this.stateService.getStatesByCountry(addressForm.getCountry()));
        }

        if (bindingResult.hasErrors()) {
            return "/user/address/addAddress";
        }

        this.addressService.addAddress(addressForm);

        redirectAttributes.addFlashAttribute(MESSAGE_ATTRIBUTE, "Address added");

        return REDIRECT_ATTRIBUTE;
    }

    @GetMapping("user/address/edit/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String editAddress(@PathVariable("id") long id, Model model) {
        model.addAttribute(Constants.PageTitle, "Edit Address");
        AddressDTO addressDTO = this.addressService.getAddressById(id);
        model.addAttribute("editAddressForm", RestaurantUtils.mapAddressDTOToEditAddressForm(addressDTO));
        model.addAttribute(COUNTRIES_ATTRIBUTE, this.countryService.getCountries());
        model.addAttribute(STATES_ATTRIBUTE, this.stateService.getStatesByCountry(addressDTO.country()));
        return "user/address/editAddress";
    }

    @PostMapping("user/address/edit/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String editAddress(@PathVariable("id") long id, @Valid @ModelAttribute("editAddressForm") EditAddressForm editAddressForm, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute(Constants.PageTitle, "Edit Address");
        model.addAttribute(COUNTRIES_ATTRIBUTE, this.countryService.getCountries());
        if (editAddressForm.getCountry() != null) {
            model.addAttribute(STATES_ATTRIBUTE, this.stateService.getStatesByCountry(editAddressForm.getCountry()));
        }
        if (bindingResult.hasErrors()) {
            return "/user/address/editAddress";
        }

        this.addressService.editAddress(editAddressForm);

        redirectAttributes.addFlashAttribute(MESSAGE_ATTRIBUTE, "Address updated");

        return REDIRECT_ATTRIBUTE;
    }

    @PostMapping("/user/address/delete")
    @PreAuthorize("hasAuthority('USER')")
    public String deletePayment(long addressId, RedirectAttributes redirectAttributes) {
        this.addressService.deleteAddress(addressId);
        redirectAttributes.addFlashAttribute(MESSAGE_ATTRIBUTE, "Address deleted");
        return REDIRECT_ATTRIBUTE;
    }

    @PostMapping("user/addressModal/add")
    @PreAuthorize("hasAuthority('USER')")
    public AddressModalDTO addAddressModal(@Valid @ModelAttribute("addressModal") AddressForm addressForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return new AddressModalDTO(null, bindingResult.getAllErrors());
        }
        AddressDTO addressDTO = this.addressService.addAddress(addressForm);
        return new AddressModalDTO(addressDTO, null);
    }

}
