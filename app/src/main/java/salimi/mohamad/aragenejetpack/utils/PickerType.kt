package salimi.mohamad.aragenejetpack.utils

sealed class PickerType{
    object Year: PickerType()
    object Month: PickerType()
    object Day: PickerType()
}