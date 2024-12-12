package salimi.mohamad.aragenejetpack.helper

sealed class ConnectionsStatus {
    object Available:ConnectionsStatus()
    object UnAvailable:ConnectionsStatus()
}